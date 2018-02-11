package com.qibao.user.dao;


import com.qibao.common.mapper.IBaseMapper;
import com.qibao.user.entity.UserAccountEO;

import java.util.List;
import java.util.Map;

public interface UserAccountEOMapper extends IBaseMapper<UserAccountEO> {

    List<UserAccountEO> listUserAccountByMap(Map<String, Object> params);

    int countByMap(Map<String, Object> params);

    double sumToatalGold(Map<String, Object> params);
}