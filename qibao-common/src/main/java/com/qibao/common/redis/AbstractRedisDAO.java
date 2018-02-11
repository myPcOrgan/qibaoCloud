package com.qibao.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;

import javax.annotation.PostConstruct;

public abstract class AbstractRedisDAO {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 字符类型操作
     */
    protected ValueOperations<String, String> valueOps;

    /**
     * set类型操作
     */
    protected SetOperations<String, String> setOps;

    /**
     * zSet类型操作
     */
    protected ZSetOperations<String, String> zSetOps;

    /**
     * List类型操作
     */
    protected ListOperations<String, String> listOps;

    public AbstractRedisDAO() {

    }

    @PostConstruct
    private void afterInitialization(){
        this.valueOps = getTemplate().opsForValue();
        this.setOps = getTemplate().opsForSet();
        this.zSetOps = getTemplate().opsForZSet();
        this.listOps = getTemplate().opsForList();
    }

    /**
     * @return the template
     */
    protected abstract StringRedisTemplate getTemplate();
}
