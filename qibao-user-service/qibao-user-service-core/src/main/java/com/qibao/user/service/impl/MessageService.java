package com.qibao.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.qibao.common.service.abs.BaseService;
import com.qibao.user.dao.MessageEOMapper;
import com.qibao.user.entity.MessageEO;
import com.qibao.user.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MessageService extends BaseService<MessageEO> implements IMessageService {

    @Autowired
    MessageEOMapper messageEOMapper;

    @Override
    public List<MessageEO> listMessageByMap(Map<String, Object> params) {
        if (params.containsKey("page") && params.containsKey("size")){
            Integer page = (Integer) params.get("page");
            Integer size = (Integer) params.get("size");
            PageHelper.startPage(page,size);
        }
        if (params.containsKey("orderBy") && params.containsKey("isAsc")) {
            Boolean isAsc = (Boolean)params.get("isAsc");
            if (isAsc) {
                params.put("ORDER","ASC");
            }else{
                params.put("ORDER","DESC");
            }
        }
        List<MessageEO> messageEOS = messageEOMapper.listMessageByMap(params);
        return messageEOS;
    }

    @Override
    public int countByMap(Map<String, Object> params) {
        int num = messageEOMapper.countByMap(params);
        return num;
    }

    @Override
    public Double getSumByMap(Map<String, Object> params) {
        return messageEOMapper.getSumByMap(params);
    }
}
