package com.qibao.activity.enums;

/**
 * 是否删除，适用所有逻辑删除
 */
public enum IsDeletedEnum {

    NO((byte) 0,"未删除"),
    YES((byte) 1,"已删除"),
    ;

    private byte code;
    private String name;

    private IsDeletedEnum(byte code, String name){
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
