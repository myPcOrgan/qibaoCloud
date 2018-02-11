package com.qibao.activity.enums;

/**
 * 开奖状态
 */
public enum LotteryStatusEnum {

    LOTTERY_NO_START((byte) 0,"开奖未开始"),
    LOTTERY_RUNNING((byte) 1,"开奖中"),
    LOTTERY_END((byte) 2,"开奖结束"),
    ;

    private byte code;
    private String name;

    private LotteryStatusEnum(byte code, String name){
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
