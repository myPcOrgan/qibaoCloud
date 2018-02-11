package com.qibao.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author LinShu
 */
public class SimpleThreadPool implements ThreadFactory {
    private final String namePrefix;
    private final ThreadGroup group;
    private final AtomicLong count;
    private final Logger logger = LoggerFactory.getLogger(SimpleThreadPool.class);

    public SimpleThreadPool(String namePrefix) {
        this(namePrefix, null);
    }

    public SimpleThreadPool(String namePrefix, ThreadGroup group) {
        this.namePrefix = namePrefix;
        this.group = group;
        this.count = new AtomicLong();
    }

    @Override
    public Thread newThread(Runnable target) {
        Thread thread = new Thread(this.group, target, this.namePrefix + "-" + this.count.incrementAndGet());

        logger.info(namePrefix + ": create new thread[" + this.count.get() + "]");

        return thread;
    }
}
