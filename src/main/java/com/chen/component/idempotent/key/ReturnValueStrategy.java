package com.chen.component.idempotent.key;

import com.chen.component.idempotent.annotation.ChenIdempotent;

/**
 * @author chen
 * @date 2017/9/12 19:44
 */
public interface ReturnValueStrategy {
    Object value(ChenIdempotent idempotent);
}
