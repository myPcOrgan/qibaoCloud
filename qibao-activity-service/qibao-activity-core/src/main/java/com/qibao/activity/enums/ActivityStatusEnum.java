package com.qibao.activity.enums;

/**
 * 活动状态
 */
public enum ActivityStatusEnum {

    ACTIVITY_END((byte) 0,"活动已结束"),
    ACTIVITY_RUN((byte) 1,"活动已正在进行中"),
    ACTIVITY_CREATE((byte) 2,"活动已创建"),
    ACTIVITY_FORBID((byte) 4,"被禁止"),
    ACTIVITY_SHUTDOWN((byte) 5,"活动关闭")
    ;

    private byte code;
    private String name;

    private ActivityStatusEnum(byte code, String name){
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
