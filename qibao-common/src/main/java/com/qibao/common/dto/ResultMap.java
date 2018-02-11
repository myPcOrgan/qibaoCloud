package com.qibao.common.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Link
 */
public class ResultMap extends JSONObject implements IResultMap {

    protected static final String CODE_KEY = "code";

    protected static final String MSG_KEY = "msg";

    protected static final Integer SUCCESS_CODE = 1;

    protected static final Integer FAIL_CODE = 0;

    protected static final Integer ERROR_CODE = -1;

    @Override
    public void success() {
        this.put(CODE_KEY, SUCCESS_CODE);
    }

    @Override
    public void success(String msg) {
        this.put(CODE_KEY, SUCCESS_CODE);
        this.put(MSG_KEY, msg);
    }

    @Override
    public void fail() {
        this.put(CODE_KEY, FAIL_CODE);
    }

    @Override
    public void fail(String msg) {
        this.put(CODE_KEY, FAIL_CODE);
        this.put(MSG_KEY, msg);
    }

    @Override
    public void error() {
        this.put(CODE_KEY, ERROR_CODE);
    }

    @Override
    public void error(String msg) {
        this.put(CODE_KEY, ERROR_CODE);
        this.put(MSG_KEY, msg);
    }
}
