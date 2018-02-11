package com.qibao.user.exceptions;

import com.qibao.common.contants.BaseExceptionMessage;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class UserGlobalExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserGlobalExceptionHandler.class);

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

    @ExceptionHandler(value = UserException.class)
    public BaseResponse userExceptionHandler(HttpServletRequest request, BaseException e){
        BaseResponse response = new BaseResponse();
        LOGGER.error(ExceptionUtil.getPrintStackTrace(e,request));
        response.setErrorMessage(e.getErrorMsg());
        return response;
    }
}
