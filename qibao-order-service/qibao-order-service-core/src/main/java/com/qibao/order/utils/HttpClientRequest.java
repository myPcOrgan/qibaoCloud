package com.qibao.order.utils;

import com.alibaba.fastjson.JSON;
import com.qibao.common.service.abs.BaseService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;


public class HttpClientRequest extends BaseService{
    public static String getClientRequest(String url, Map<String, Object> map) {
        HttpResponse httpResponse = null;
        InputStream content = null;
        String responseStr;
        HttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type","application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            //将参数转换成json格式
            String str = JSON.toJSONString(map);
            StringEntity entity = new StringEntity(str, Charset.forName("utf-8"));// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpResponse = httpClient.execute(httpPost);
            content = httpResponse.getEntity().getContent();
            responseStr = StreamIOHelper.inputStreamToStr(content, "utf-8");
            return responseStr;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.getConnectionManager().shutdown();
                }
                if (content != null) {
                    content.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info(e.getMessage());
            }
        }
        return "";
    }
}
