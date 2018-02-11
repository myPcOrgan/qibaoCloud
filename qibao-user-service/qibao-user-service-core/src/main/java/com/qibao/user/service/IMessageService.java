package com.qibao.user.service;

import com.qibao.common.service.IBaseService;
import com.qibao.user.entity.MessageEO;

import java.util.List;
import java.util.Map;

public interface IMessageService extends IBaseService<MessageEO> {

    List<MessageEO> listMessageByMap(Map<String, Object> params);

    int countByMap(Map<String, Object> params);

    Double getSumByMap(Map<String, Object> params);
}
