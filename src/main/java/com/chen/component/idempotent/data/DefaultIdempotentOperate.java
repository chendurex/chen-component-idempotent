package com.chen.component.idempotent.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chen
 * @date 2017/9/13 13:54
 */
class DefaultIdempotentOperate implements IdempotentOperate {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public boolean proceed(String key, int expiredTime) {
        boolean suc = JedisUtil.setnx(key, key, expiredTime);
        if (!suc) {
            log.warn("当前操作已经被当做重复执行的操作，key为：{}", key);
        }
        return suc;
    }

    @Override
    public void callback(String key) {
        JedisUtil.delValFromJedis(key);
    }
}
