package com.qibao.frontend.interceptor;

import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * feign调用异常统一处理
 */
@Aspect
@Component
public class FeignExceptionHandler {

    @Pointcut("execution(public * com.qibao.frontend.feign..*.*(..))")
    public void catchException(){}


    @AfterReturning(returning = "response", pointcut = "catchException()")
    public void doAfterReturning(BaseResponse response){
        if (response != null && "01".equals(response.getCode())){
            throw new BaseException(1,response.getMessage());       //有异常统一抛出BaseException，然后全局异常器捕获
        }
    }
}
