package com.qibao.user.context.enums;

/**
 * Created by 340067 on 2018/1/26.
 */
public enum UserGradeEnum {

    GENERAL(1, "普通用户"),


    OFFICIAL(2, "官方用户"),


    ANCHOR(3, "主播");

    private Integer code;

    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    UserGradeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserGradeEnum getTypeByCode(Integer code) {
        for (UserGradeEnum type : UserGradeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未找到匹配的类型：UserGradeEnum:" + code);
    }
}
