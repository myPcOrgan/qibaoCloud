package com.qibao.payment.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一收单下单并支付页面
 * Created by ljn on 2018/2/1.
 */
@RestController
@RequestMapping("payment")
public class AlipayTradePay extends HttpServlet {



    @Value("${AlipayConfig.APP_ID}")
    private String APP_ID = "";

    @Value("${AlipayConfig.APP_PRIVATE_KEY}")
    private String APP_PRIVATE_KEY = "";

    @Value("${AlipayConfig.FORMAT}")
    private String FORMAT = "";

    @Value("${AlipayConfig.CHARSET}")
    private String CHARSET = "";

    @Value("${AlipayConfig.ALIPAY_PUBLIC_KEY}")
    private String ALIPAY_PUBLIC_KEY = "";

    @Value("${AlipayConfig.SIGN_TYPE}")
    private String SIGN_TYPE = "";

    //付款成功后返回页面地址
    @Value("${AlipayConfig.ReturnUrl}")
    private String RETURN_URL = "";

    @Value("${AlipayConfig.NotifyUrl}")
    private String NOTIFY_URL = "";


    @Override
    @RequestMapping(value="alipay")
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(RETURN_URL);
        alipayRequest.setNotifyUrl(NOTIFY_URL);//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101003\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.99," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
