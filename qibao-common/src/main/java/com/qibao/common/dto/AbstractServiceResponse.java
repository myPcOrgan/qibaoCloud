package com.qibao.common.dto;

public abstract class AbstractServiceResponse implements IServiceResponse {

    private final static String SUCCESS_CODE = "00";

    private final static String FAIL_CODE = "01";

    private String code = SUCCESS_CODE;     //默认成功
    private String message;

    /**
     * 返回错误码和错误信息
     * @param message
     */
    public void setErrorMessage(String message){
        this.code = FAIL_CODE;
        this.message = message;
    }

    public void setSuccess(){
        this.code = SUCCESS_CODE;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
