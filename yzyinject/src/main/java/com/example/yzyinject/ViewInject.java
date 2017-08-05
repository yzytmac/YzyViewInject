package com.example.yzyinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yzy on 2017/8/5.
 * 根据i的绑定控件
 * 注意要在onCreate方法中调用ViewInject（this）
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface ViewInject {
    int value() default -1;
}
