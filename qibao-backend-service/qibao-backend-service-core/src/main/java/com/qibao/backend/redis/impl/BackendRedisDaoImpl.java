package com.qibao.backend.redis.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qibao.backend.entity.FunctionEO;
import com.qibao.backend.keyHelper.BaseKeyHelper;
import com.qibao.backend.redis.IBackendRedisDao;
import com.qibao.common.redis.AbstractRedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周黎钢 on 2018/2/5.
 */
@Component
public class BackendRedisDaoImpl extends AbstractRedisDAO implements IBackendRedisDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected StringRedisTemplate getTemplate() {
        return stringRedisTemplate;
    }
    @Override
    public void setBackendFunctions(List<FunctionEO> functionEOs){
        String functionEOS=JSON.toJSONString(functionEOs);
        valueOps.set(BaseKeyHelper.getBackendFunctionsKey(),functionEOS);
    }
    @Override
    public List<FunctionEO> getBackendFunctions(){
        String functionEOS=valueOps.get(BaseKeyHelper.getBackendFunctionsKey());
        List<FunctionEO>list= JSON.parseObject(functionEOS, new TypeReference<ArrayList<FunctionEO>>(){});
        return list;
    }
}
