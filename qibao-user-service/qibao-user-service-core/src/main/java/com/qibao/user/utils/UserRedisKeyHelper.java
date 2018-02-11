package com.qibao.user.utils;


public class UserRedisKeyHelper {
    private static final String keyPrefix = "qibao:user:";

    /**
     * 用户信息保存缓存
     *
     * @param
     * @return
     */
    public static String userInfoRedis(String id) {
        return keyPrefix+"info:" + id;
    }

    public static String userPhoneVerifyCodeRedis(String phone) {
        return keyPrefix+"phone:" + phone;
    }

    public static String userSessionCreate(String id) {
        return keyPrefix + "cookie:" + "userId:" + id;
    }

    public static String userSessionCreateKey(String authkey) {
        return keyPrefix + "cookie:" + "authkey:" + authkey;
    }

    /**
     * sessionId->ip映射
     *
     * @param sessionId
     * @return
     */
    public static String sessionId2Ip(String sessionId) {
        return keyPrefix + "ip:" + "sessionId2Ip:" + sessionId;
    }

    /**
     * ip->sessionId映射
     * @param ip
     * @return
     */
    public static String ip2SessionId(String ip) {
        return keyPrefix + "ip:" + "ip2SessionId:" + ip;
    }


    public static String messageUserIpNumIncrease(String userIp){
        return keyPrefix + "sendMessage:" + "messageNum:" + userIp;
    }

    public static String messageDayNumIncrease(){
        return keyPrefix + "sendMessage:" + "dayMessageNum:" + "messageDayNumIncrease";
    }

    public static String messageDayTotalNumDay(){
        return keyPrefix + "sendMessage:" + "dayMessageNum:" + "messageDayTotalNumDay";
    }

}
