package com.qibao.activity.enums;

public enum UserPrizeTypeEnum {
    /**
     * 房间中奖
     */
    USERPRIZETYPE1(1),

    /**
     * 夺金中奖
     */
    USERPRIZETYPE2(2),

    /**
     * 开箱消耗金币
     */
    USERPRIZETYPE3(3),

    /**
     * 创建房间消耗金币
     */
    USERPRIZETYPE4(4);

    private Integer code;


    private UserPrizeTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public static UserPrizeTypeEnum getTypeByCode(Integer code) {
        for (UserPrizeTypeEnum type : UserPrizeTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未找到匹配的类型：GoldTypeEnum:" + code);
    }
}
