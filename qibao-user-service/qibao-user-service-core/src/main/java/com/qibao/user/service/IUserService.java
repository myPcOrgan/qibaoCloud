package com.qibao.user.service;

import com.qibao.common.service.IBaseService;
import com.qibao.user.entity.UserAccountEO;

import java.util.List;
import java.util.Map;

/**
 * Created by 340067 on 2018/1/10.
 */
public interface IUserService extends IBaseService<UserAccountEO> {


    UserAccountEO getUserInfoById(Long userId);

    Long insertUserInfo(UserAccountEO userAccountEO);

    void updateUserInfo(UserAccountEO userAccountEO);

    UserAccountEO checkQQExist(String message, Integer loginType);

    List<UserAccountEO> listUserInfoByIds(List<Long> userIds);

    List<UserAccountEO> listUserAccountByMap(Map<String, Object> params);

    int countByMap(Map<String, Object> params);

    void updateUserGold(Long userId, Integer type, Double goldNum, String desp);

    Double sumToatalGold(Map<String, Object> params);

}
