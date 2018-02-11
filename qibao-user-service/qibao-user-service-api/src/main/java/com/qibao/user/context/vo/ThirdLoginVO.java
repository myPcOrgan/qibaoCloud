package com.qibao.user.context.vo;

public class ThirdLoginVO extends UserAccountVO {
    private String url;

    private String authkey;

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
