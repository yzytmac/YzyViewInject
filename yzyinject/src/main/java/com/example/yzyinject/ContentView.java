package com.example.yzyinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yzy on 2017/8/5.
 * 绑定布局，在activity最上方@ContentView（R.layout.xxx）
 * 注意要在onCreate方法中调用ViewInject（this）
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ContentView {
    int value() default -1;
}
