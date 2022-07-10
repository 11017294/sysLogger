package com.chen.sysLogger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-07-10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogger {

    /**
     * 行为
     */
    EBehavior value();

    /**
     * 业务名称（只做注释）
     */
     String name() default "";

    /**
     * 是否将当前日志记录到数据库中
     */
    boolean save() default true;
}
