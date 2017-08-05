package com.example.yzyinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yzy on 2017/8/5.
 * 为控件设置onClick方法
 * 在要执行的方法上@Click（{R.id.xx1,R.id.xx2...}）,R.id.xx是被绑定的控件
 * 要执行的方法名随意，可以无参，可以有一个view参数
 * 注意要在onCreate方法中调用ViewInject（this）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Click {
    int[] value();
}
