package com.qibao.user.context.enums;

/**
 * Created by 340067 on 2018/1/26.
 */
public enum UpdateUserTypeEnum {
    /**
     * 启用用户
     */
    ENABLEUSER(1, "启用用户"),

    /**
     * 禁用用户
     */
    DISABLEUSER(2, "禁用用户"),

    /**
     * 删除用户
     */
    DELUSER(3, "删除用户"),
    /**
     * 启用房主
     */
    ENABLEROOM(4, "启用房主"),

    /**
     * 禁用房主
     */
    DISABLEROOM(5, "禁用房主");

    private Integer code;

    private String name;

    public String getName() {
        return name;
    }


    UpdateUserTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return this.code;
    }

    public static UpdateUserTypeEnum getTypeByCode(Integer code) {
        for (UpdateUserTypeEnum type : UpdateUserTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未找到匹配的类型：UpdateUserTypeEnum:" + code);
    }
}
