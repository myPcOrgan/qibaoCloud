package com.qibao.user.enums;

/**
 * Created by 340067 on 2018/1/10.
 */
public enum ThirdLoginTypeEnum {
    /**
     * qq
     */
    THIRDQQ(1,"QQ"),

    /**
     * 微信
     */
    THIRDWECHAT(2,"WeChat"),

    /**
     * Sina
     */
    PHONE(3,"Phone");

    private Integer code;

    private String name;

    private ThirdLoginTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getIdPrefix() {
        return name;
    }

    public static ThirdLoginTypeEnum getTypeByCode(Integer code) {
        for (ThirdLoginTypeEnum type : ThirdLoginTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未找到匹配的类型：ThirdLoginTypeEnum:" + code);
    }







}
