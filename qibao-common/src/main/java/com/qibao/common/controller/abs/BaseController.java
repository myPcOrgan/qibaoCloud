package com.qibao.common.controller.abs;

import com.qibao.common.controller.IBaseController;
import com.qibao.common.dto.IResultMap;
import com.qibao.common.dto.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Link
 */
public abstract class BaseController implements IBaseController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    protected IResultMap success() {
        IResultMap resultMap = new ResultMap();
        resultMap.success();
        return resultMap;
    }

    protected IResultMap success(String msg) {
        IResultMap resultMap = new ResultMap();
        resultMap.success(msg);
        return resultMap;
    }

    protected IResultMap fail() {
        IResultMap resultMap = new ResultMap();
        resultMap.fail();
        return resultMap;
    }

    protected IResultMap fail(String msg) {
        IResultMap resultMap = new ResultMap();
        resultMap.success(msg);
        return resultMap;
    }

    protected IResultMap error() {
        IResultMap resultMap = new ResultMap();
        resultMap.error();
        return resultMap;
    }

    protected IResultMap error(String msg) {
        IResultMap resultMap = new ResultMap();
        resultMap.error(msg);
        return resultMap;
    }
}
