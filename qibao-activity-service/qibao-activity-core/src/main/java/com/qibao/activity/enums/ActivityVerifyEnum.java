package com.qibao.activity.enums;

/**
 * 活动审核状态
 */
public enum ActivityVerifyEnum {

    VERIFY_WAIT((byte) 0,"待审核"),
    VERIFY_PASS((byte) 1,"审核通过"),
    VERIFY_FORBID((byte) 2,"审核不通过"),
    ;

    private byte code;
    private String name;

    private ActivityVerifyEnum(byte code, String name){
        this.code = code;
        this.name = name;
    }

    public byte code() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
