package com.qibao.common.utils;

import com.qibao.common.contants.BaseExceptionMessage;
import com.qibao.common.exception.BaseException;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class ExceptionUtil {


    public static String getPrintStackTrace(Exception e,HttpServletRequest request) {
        return getPrintStackTrace(null,request,e);
    }


    public static String getPrintStackTrace(BaseException e,HttpServletRequest request) {
        return getPrintStackTrace(e,request,null);
    }

    public static String getPrintStackTrace(BaseException be){
        return getPrintStackTrace(be,null,null);
    }

    public static String getPrintStackTrace(Exception e){
        return getPrintStackTrace(null,null,e);
    }

    /**
     * 记录异常信息
     * @param be
     * @param request
     * @param e
     * @return
     */
    public static String getPrintStackTrace(BaseException be, HttpServletRequest request, Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("---------------------------------------错误信息分割线-------------------------------------------------------");
        sb.append("\n");
        sb.append("请求时间："+ DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        sb.append("\n");
        if (request != null){
            String queryString = request.getQueryString();
            queryString = queryString != null ? (request.getRequestURI() + "?" + queryString) : request.getRequestURI();
            sb.append("url:"+queryString);
            sb.append("\n");
        }
        if (be != null){
            sb.append("错误信息:");
            sb.append(be.getErrorMsg());
            sb.append("\n");
            StringWriter sw = new StringWriter();
            be.printStackTrace(new PrintWriter(sw, true));
            sb.append("异常栈信息:"+sw.toString());
        }else {
            sb.append("错误信息:");
            sb.append(BaseExceptionMessage.SYSTEM_EXCEPTION);
            sb.append("\n");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            sb.append("异常栈信息:"+sw.toString());
        }
        return sb.toString();
    }

}
