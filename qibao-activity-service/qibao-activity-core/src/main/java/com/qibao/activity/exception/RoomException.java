package com.qibao.activity.exception;

import com.qibao.common.exception.BaseException;

public class RoomException extends BaseException {

    private static final Integer errorCode = 1;     //业务错误

    public static final String USER_ID_EMPTY = "用户id不能为空！";

    public static final String ACTIVITY_ID_EMPTY = "活动id不能为空！";

    public static final String USER_NOT_EXIT = "用户不存在！";

    public static final String ROOM_NOT_EXIT = "该房间不存在！";

    public static final String ROOM_ACTIVITY_NOT_EXIT = "该活动不存在！";

    public static final String ROOM_ID_EMPTY = "房间id为空！";

    public static final String ROOM_IS_USED = "该房间正在使用中，不能操作！";

    public static final String ROOM_IS_SHUTDOWN = "该房间已关闭，不能操作！";

    public static final String ROOM_IS_NOT_START = "该房间没有即将开始的活动！";

    public static final String ACTIVITY_IS_UPVOTE = "该活动您已经点赞，不能再次点赞！";

    public static final String ROOM_VERIFY_FAIL = "该房间审核不通过！";

    public static final String ROOM_VERIFY_WAIT = "该房间审核中！";

    public static final String ROOM_ACTIVITY_EXIT = "该房间已有活动！";

    public static final String ROOM_TOTAL_NUM = "一个用户最多只能创建10个房间！";

    public static final String ROOM_TOTAL_GOLD_LESS = "您的剩余金币不足，请充值！";

    public static final String ROOM_LOTIFY_ERROR = "房间开奖异常！";

    public static final String ROOM_ACTIVITY_ID_NULL = "房间活动id不能为空！";

    public static final String USER_EXIT_ACTIVITY = "用户已经加入了该活动！";

    public static final String USER_LIVING_ROOM = "直播间地址不能为空！";

    public static final String USER_LOTTY_TIME_ERROR = "活动开奖时间错误！";

    public static final String USER_ROOM_PASSWORD = "用户房间密码不能为空！";

    public static final String USER_ROOM_NAME_EMPTY = "用户房间名称不能为空！";

    public static final String USER_JOIN_PEOPLE = "房间参与人数不能小于0！";

    public static final String USER_CREATE_ROOM_MAX = "一个用户最多只能创建10个房间！";

    public static final String USER_TOTAL_GOLD_LESS = "您的金币不足，请充值！";

    public static final String ACTIVITY_END_ERROR = "活动已结束！";

    public static final String ACTIVITY_FORBID_ERROR = "该活动已被禁止！";

    public static final String ROOM_OPEN_ERROR = "该房间已经开启！";

    public static final String ROOM_REFULSE = "该房间被没收不能开启！";

    public static final String ROOM_VERIFY_ERROR = "该房间已经审核过了，不能多次审核！";

    public static final String ACTIVITY_NOT_FORBID = "该活动并未关闭，不用开启！";

    public static final String ACTIVITY_PASSWORD_ERROR = "活动密码不正确！";

    public static final String ACTIVITY_YZM_ERROR = "活动密码不正确！";

    public static final String ACTIVITY_ROOM_ERROR = "房间或者活动不存在！";

    public static final String ACTIVITY_RUNNING = "活动正在进行中不能关闭！";

    public static final String ACTIVITY_SHUTDOWN_ERROR = "活动已关闭！";

    public RoomException( String errorMsg) {
        super(errorCode, errorMsg);
    }
}
