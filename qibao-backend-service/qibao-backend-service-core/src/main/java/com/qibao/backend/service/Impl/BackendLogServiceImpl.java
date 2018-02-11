package com.qibao.backend.service.Impl;

import com.github.pagehelper.PageHelper;
import com.qibao.backend.entity.BackendLogEO;
import com.qibao.backend.mapper.BackendLogEOMapper;
import com.qibao.backend.service.IBackendLogService;
import com.qibao.common.service.abs.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by 340067 on 2018/1/25.
 */
@Component
public class BackendLogServiceImpl extends BaseService<BackendLogEO> implements IBackendLogService {

    @Autowired
    BackendLogEOMapper backendLogEOMapper;


    @Override
    public List<BackendLogEO> listMessageByMap(Map<String, Object> params) {
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
        List<BackendLogEO> backendLogEOs = backendLogEOMapper.listBackendLogByMap(params);
        return backendLogEOs;
    }

    @Override
    public int countByMap(Map<String, Object> params) {
        int num = backendLogEOMapper.countByMap(params);
        return num;
    }


}
