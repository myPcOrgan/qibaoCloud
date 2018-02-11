package com.qibao.user.context.enums;

public enum SysMessageTypeEnum {
    LOGIN(0,"登录"),
    SYSTEM(1,"系统");

    private Integer code;

    private String name;

    SysMessageTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getName() {
        return name;
    }

    public static SysMessageTypeEnum getMessageTypeByCode(Integer code) {
        for (SysMessageTypeEnum type : SysMessageTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未能找到匹配的SysMessageTypeEnum:" + code);
    }
}
