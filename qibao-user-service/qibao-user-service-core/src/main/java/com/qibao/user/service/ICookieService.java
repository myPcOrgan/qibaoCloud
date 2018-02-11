package com.qibao.user.service;


public interface ICookieService{

    String createSession(Long userId);

    void delUserSession(Long userId);

    String createIpSession(String ip);
}
