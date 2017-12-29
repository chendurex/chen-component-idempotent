package com.chen.component.idempotent.key;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author chen
 * @date 2017/8/1 14:53
 */
public interface IdempotentKeyStrategy {
    String key(MethodInvocation invocation);
}
