package com.qibao.goods.exception;

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
public class GoodsExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = GoodsException.class)
    public BaseResponse defaultErrorHandler(HttpServletRequest request, GoodsException e) {
        logger.error(ExceptionUtil.getPrintStackTrace(e, request));
        BaseResponse response = new BaseResponse();
        response.setErrorMessage(e.getErrorMsg());
        return response;
    }

    @ExceptionHandler(value = BaseException.class)
    public BaseResponse defaultErrorHandler(HttpServletRequest request, BaseException e) {
        logger.error(ExceptionUtil.getPrintStackTrace(e, request));
        BaseResponse response = new BaseResponse();
        response.setErrorMessage(e.getErrorMsg());
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse defaultErrorHandler(HttpServletRequest request, Exception e) {
        BaseResponse response = new BaseResponse();
        logger.error(ExceptionUtil.getPrintStackTrace(e, request));
        response.setErrorMessage(BaseExceptionMessage.SYSTEM_EXCEPTION);    //这个异常就是程序内部的异常
        return response;
    }

}
