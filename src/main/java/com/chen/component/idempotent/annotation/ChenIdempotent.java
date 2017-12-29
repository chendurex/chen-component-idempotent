package com.chen.component.idempotent.annotation;

import com.chen.component.idempotent.key.DefaultIdempotentKey;

import java.lang.annotation.*;

/**
 * example:
 * <pre>{@code
    @link @ChenIdempotent(previous = "uniqueKey", expiredTime = 20)
     public void businessWithinIdempotent(@ChenIdempotentParam String uniqueKey, @ChenIdempotentParam(value="name") Order order) {
     // business code within idempotent
     }
}</pre>
 @see ChenIdempotentParam
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface ChenIdempotent {
    /**
     * <p>定义幂等性key值的前缀值，用于区别不同的业务</p>
     * <p>更加细粒度的生成策略请参考：
     * {@link DefaultIdempotentKey}
     * </p>
     */
    String previous() default "";

    /**
     * 过期时间，默认60秒
     */
    int expiredTime() default 60;

    /**
     * 验证失败后，返回值类型
     * <p>如果是基本类型的包装类型，那么可以指定默认值，如果不指定则按基本类型的默认值返回{@link #returnValue}</p>
     * <p>如果是字符串类型：也可以当做基本基类处理</p>
     * <p>如果是自定义类型，那么需要提供一个无參的构造函数用于返回</p>
     * 默认不返回任何类型
     */
    Class<?> returnType() default Void.class;

    /**
     * 基本类型的返回值
     * int : 1 or 2 等
     * boolean : true or false
     * @see #returnType()
     * 默认返回空字符串
     */
    String returnValue() default "";
}
