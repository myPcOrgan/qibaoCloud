package com.qibao.common.utils;

public class IpContext {
    private static ThreadLocal<IpContext> context = new ThreadLocal<IpContext>() {
        protected IpContext initialValue() {
            return new IpContext();
        }
    };
    private String ip;

    public static IpContext getCurrentContext() {
        return (IpContext)context.get();
    }

    private IpContext() {
    }

    public static void setCurrentContext(String ip) {
        IpContext ipContext = getCurrentContext();
        ipContext.ip = ip;
    }

    public static String getUserIp() {
        return getCurrentContext().getIp();
    }

    public static void clean() {
        context.remove();
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
