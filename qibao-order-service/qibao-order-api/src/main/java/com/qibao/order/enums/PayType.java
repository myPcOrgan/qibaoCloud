package com.qibao.order.enums;

/**
 * Created by ljn on 2018/1/31.
 */
public enum PayType {

    AliPay (1,"支付宝"),

    WeChat (2,"微信");

    private int code;

    private String name;

    PayType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static PayType getPayType(int code) {
        for (PayType type : PayType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("未找到匹配的payType:"+code);
    }
}
