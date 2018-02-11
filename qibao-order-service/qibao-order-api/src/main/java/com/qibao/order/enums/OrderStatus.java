package com.qibao.order.enums;

/**
 * Created by ljn on 2018/1/10.
 */
public enum OrderStatus {

    WAIT_PAYMENT(1,"待付款"),

    DEPOSTI(2,"存入中"),

    GET_BACK(3,"取回中"),

    CANCELED(4,"交易取消"),

    SUCCESS(5,"交易成功"),

    PAY_FAIL(6,"付款失败");

    private int code;

    private String status;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    OrderStatus(int code, String status) {

        this.code = code;
        this.status = status;
    }

    public static OrderStatus getOrderStatus(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未找到匹配的orderStatus:"+code);
    }
}
