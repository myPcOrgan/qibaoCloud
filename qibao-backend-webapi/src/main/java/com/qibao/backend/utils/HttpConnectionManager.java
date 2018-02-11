package com.qibao.backend.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.servlet.http.Cookie;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

/**
 * httpClient连接池
 */
public class HttpConnectionManager {
    /**
     * 请求超时时间
     */
    private static final int timeOut = 6 * 1000;
    /**
     * 最大连接数
     */
    private static final int maxTotal = 800;
    /**
     * 每个路由基础的连接
     */
    private static final int defaultMaxPerRoute = 200;
    /**
     * 失败重试次数
     */
    private static final int retryCount = 3;

    private static PoolingHttpClientConnectionManager cm = null;

    //    @PostConstruct
    private static void init() {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
    }

    private static CloseableHttpClient getHttpClient() {
        if (cm == null) {
            init();
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,
                                        int executionCount, HttpContext context) {
                if (executionCount >= retryCount) {// 如果已经重试了3次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).setRetryHandler(httpRequestRetryHandler).build();
//        CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接

        return httpClient;
    }

    /**
     * lxk 2017.12.08
     * 发送请求携带cookie
     */
    public static String httpGetResult(String url, Cookie cookie) throws IOException {
        String result = "";
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = getHttpClient();
            httpGet = new HttpGet(url.toString());
            if (cookie != null && !StringUtils.isBlank(cookie.getValue())) {
                httpGet.setHeader("Cookie", ".5173auth=" + cookie.getValue());
            }
            response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            if (response != null) {
                response.close();
            }
        }
        return result;
    }

    /**
     * GET请求
     *
     * @param url
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String httpGet(String url, Map<String, Object> params, String encoding, boolean isNeedURI) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = getHttpClient();
        StringBuilder urlSb = new StringBuilder(url);

        //拼接参数到url
        if (params != null && params.size() > 0) {
            if (!url.contains("?")) {
                urlSb.append("?");
            }
            if (url.contains("?") && !url.endsWith("?")) {
                urlSb.append("&");
            }
            for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ) {
                String key = iter.next();
                String value = URLEncoder.encode(params.get(key).toString());
                urlSb.append(key).append("=").append(value).append("&");
            }
            //删除最后多余的字符
            char lastChar = urlSb.charAt(urlSb.length() - 1);
            if (lastChar == '&' || lastChar == '?') {
                urlSb.deleteCharAt(urlSb.length() - 1);
            }
        }

        HttpGet get = null;
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            if (isNeedURI) {
                URL urlStr = new URL(urlSb.toString());
                URI uri = new URI(urlStr.getProtocol(), urlStr.getHost(), urlStr.getPath(), urlStr.getQuery(), null);
                get = new HttpGet(uri);
            } else {
                get = new HttpGet(urlSb.toString());
            }

            response = httpClient.execute(get);
            in = response.getEntity().getContent();
            String responseText = IOUtils.toString(in, encoding);
            return responseText;
        } finally {
            if (get != null) {
                get.releaseConnection();
            }
            if (in != null) {
                in.close();
            }
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * GET请求
     *
     * @param url
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String httpGetByURI(String url, Map<String, Object> params, String encoding) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = getHttpClient();
        StringBuilder urlSb = new StringBuilder(url);

        //拼接参数到url
        if (params != null && params.size() > 0) {
            if (!url.contains("?")) {
                urlSb.append("?");
            }
            if (url.contains("?") && !url.endsWith("?")) {
                urlSb.append("&");
            }
            for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ) {
                String key = iter.next();
                String value = URLEncoder.encode(params.get(key).toString());
                urlSb.append(key).append("=").append(value).append("&");
            }
            //删除最后多余的字符
            char lastChar = urlSb.charAt(urlSb.length() - 1);
            if (lastChar == '&' || lastChar == '?') {
                urlSb.deleteCharAt(urlSb.length() - 1);
            }
        }

        HttpGet get = null;
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            URL urlStr = new URL(urlSb.toString());
            URI uri = new URI(urlStr.getProtocol(), urlStr.getHost(), urlStr.getPath(), urlStr.getQuery(), null);
            get = new HttpGet(uri);
            response = httpClient.execute(get);
            in = response.getEntity().getContent();
            String responseText = IOUtils.toString(in, encoding);
            return responseText;
        } finally {
            if (get != null) {
                get.releaseConnection();
            }
            if (in != null) {
                in.close();
            }
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * POST请求
     *
     * @param urlStr
     * @param params
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String httpPost(String urlStr, Map<String, Object> params, String encoding) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = getHttpClient();
        MultipartEntity multiEntity = new MultipartEntity();
        if (params != null) {
            for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ) {
                String key = iter.next();
                Object value = params.get(key);
                if (value == null || StringUtils.isBlank(value.toString())) {
                    continue;
                }

                if (value instanceof File) {
                    multiEntity.addPart(key, new FileBody((File) value));
                } else {
                    multiEntity.addPart(key, new StringBody(value.toString(), Charset.forName(encoding)));
                }
            }
        }
        HttpPost httpPost = null;
        CloseableHttpResponse httpResponse = null;
        InputStream in = null;
        try {
            URL url = new URL(urlStr);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            httpPost = new HttpPost(uri);
            httpPost.setEntity(multiEntity);
            httpResponse = httpClient.execute(httpPost);
            in = httpResponse.getEntity().getContent();
            String responseText = IOUtils.toString(in, encoding);
            return responseText;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            if (in != null) {
                in.close();
            }
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }

    /**
     * POST请求
     *
     * @param urlStr
     * @param imageFile
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String httpPost(String urlStr, MultipartFile imageFile, String encoding) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = null;
        CloseableHttpResponse httpResponse = null;
        InputStream in = null;
        try {
            URL url = new URL(urlStr);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            httpPost = new HttpPost(uri);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody(imageFile.getName(), imageFile.getInputStream(), ContentType.create("multipart/form-data"), imageFile.getName());
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            httpResponse = httpClient.execute(httpPost);
            in = httpResponse.getEntity().getContent();
            String responseText = IOUtils.toString(in, encoding);

            return responseText;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            if (in != null) {
                in.close();
            }
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }


    public static String trimJsonFormat(String jsonStr) {
        jsonStr = jsonStr.substring(jsonStr.indexOf("{"));
        jsonStr = jsonStr.substring(0, jsonStr.lastIndexOf("}") + 1);
        return jsonStr;
    }

}
