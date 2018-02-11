package com.qibao.user.context.enums;

/**
 * Created by 340067 on 2018/1/26.
 */
public enum GoldTypeEnum {
    /**
     * 总金币添加
     */
    ADDTOTALGOLD(1, "总金币添加"),

    /**
     * 总金币减少
     */
    REDUCETOTALGOLD(2, "总金币减少"),

    /**
     * 连续超本金次数加一，总金币加
     */
    ALLWINNUM(3, "连续超本金次数加一，总金币加"),

    /**
     *连续超本金次数置空，总金币加
     */
    ALLWINNUMNULL(4, "连续超本金次数置空，总金币加");

    private Integer code;

    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    GoldTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GoldTypeEnum getTypeByCode(Integer code) {
        for (GoldTypeEnum type : GoldTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未找到匹配的类型：GoldTypeEnum:" + code);
    }
}
