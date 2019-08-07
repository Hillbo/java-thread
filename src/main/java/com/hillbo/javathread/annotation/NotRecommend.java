package com.hillbo.javathread.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记【不推荐】的类或者是写法
 */

// Target指注解作用的目标
@Target(ElementType.TYPE)

// Retention指注解存在的范围
// SOURCE在编译的时候会被忽略掉
// RUNTIME标识注解会在字节码文件中存在，在运行时通过反射可以拿到
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {

    String value() default "";

}
