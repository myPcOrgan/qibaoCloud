package com.qibao.order.aop;

import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by ljn on 2018/2/9.
 */
@Aspect
@Component
public class FeignExceptionHandler {

    @Pointcut("execution(public * com.qibao.order.feign..*.*(..))")
    public void catchException(){}

    @AfterReturning(returning = "response", pointcut = "catchException()")
    public void doAfterReturning(BaseResponse response){
        if (response != null && "01".equals(response.getCode())){
            throw new BaseException(1,response.getMessage());
        }
    }
}
