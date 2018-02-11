package com.qibao.backend.mapper;

import com.qibao.backend.entity.UserEO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface UserEOMapper extends IBaseMapper<UserEO> {
    List<UserEO> selectWithRole(Map map);
}