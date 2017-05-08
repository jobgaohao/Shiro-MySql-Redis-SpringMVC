package com.sojson.common.aopLog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <pre>
 * AOP 记录日志
 * </pre>
 *
 * @author hao.gao
 * @version $Id: SystemLog.java, v 0.1 2017年5月5日 下午2:53:19 hao.gao Exp $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface SystemLog {
   public String value() default "";
}
