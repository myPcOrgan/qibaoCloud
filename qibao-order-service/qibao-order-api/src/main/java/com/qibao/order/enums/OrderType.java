package com.qibao.order.enums;

/**
 * Created by ljn on 2018/1/10.
 */
public enum OrderType {

    MALL_BUY(1,"商城购买","QBGM"),

    DEPOSIT(2,"存入","QBCR"),

    GET_BACK(3,"取回","QBQH"),

    ADDITIONAL_ORDER(4,"补单","QBBD");

    private int code;

    private String type;

    private String prefix;

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getPrefix() {
        return prefix;
    }

    OrderType(int code, String type, String prefix) {
        this.code = code;
        this.type = type;
        this.prefix = prefix;
    }

    public static OrderType getOrderType(int code) {
        for (OrderType type : OrderType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("未找到匹配的orderType:"+code);
    }
}
