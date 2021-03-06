package com.qibao.order.exception;

import com.qibao.common.contants.BaseExceptionMessage;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ljn on 2018/1/30.
 */
@RestControllerAdvice
public class OrderExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public BaseResponse defaultErrorHandler(HttpServletRequest request, Exception e){
        BaseResponse response = new BaseResponse();
        LOGGER.error(ExceptionUtil.getPrintStackTrace(e,request));
        response.setErrorMessage(BaseExceptionMessage.SYSTEM_EXCEPTION);    //这个异常就是程序内部的异常
        return response;
    }


    @ExceptionHandler(value = BaseException.class)
    public BaseResponse baseExceptionHandler(HttpServletRequest request, BaseException e){
        BaseResponse response = new BaseResponse();
        response.setErrorMessage(e.getErrorMsg());
        LOGGER.error(ExceptionUtil.getPrintStackTrace(e, request));
        return response;
    }

    @ExceptionHandler(value = OrderException.class)
    public BaseResponse baseExceptionHandler(HttpServletRequest request, OrderException e){
        BaseResponse response = new BaseResponse();
        response.setErrorMessage(e.getErrorMsg());
        LOGGER.error(ExceptionUtil.getPrintStackTrace(e, request));
        return response;
    }



}
