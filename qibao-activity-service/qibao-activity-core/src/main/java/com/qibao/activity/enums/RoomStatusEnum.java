package com.qibao.activity.enums;

/**
 * 房间状态
 */
public enum RoomStatusEnum {
    FORBID((byte) 0,"禁止"),
    NORMAL((byte) 1,"正常"),
    ACTIVITY((byte) 2,"活动中"),
    REFUSE((byte) 3,"没收"),
    ;

    private byte code;
    private String name;

    private RoomStatusEnum(byte code, String name){
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
