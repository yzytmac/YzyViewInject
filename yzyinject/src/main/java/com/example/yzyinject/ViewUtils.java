package com.example.yzyinject;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yzy on 2017/8/5.
 */

public class ViewUtils {
    public static void inject(final Activity pActivity) {
        if(pActivity.getClass().isAnnotationPresent(ContentView.class)) {
            ContentView vAnnotation = pActivity.getClass().getAnnotation(ContentView.class);
            int layout = vAnnotation.value();
            if(layout == -1) {
                Log.e("yzy", "布局layout没找到" );
            }
            pActivity.setContentView(layout);
        }



        //获取所有的属性注解
        Field[] vFields = pActivity.getClass().getDeclaredFields();
        for (Field vField : vFields) {
            if (vField.isAnnotationPresent(ViewInject.class)) {
                ViewInject vAnnotation = vField.getAnnotation(ViewInject.class);
                int id = vAnnotation.value();//这个就是去除注解中的id值
                if(id == -1) {
                    Log.e("yzy", "id没找到" );
                }
                vField.setAccessible(true);
                View vViewById = pActivity.findViewById(id);
                try {
                    vField.set(pActivity, vViewById);
                } catch (IllegalAccessException pE) {
                    pE.printStackTrace();
                    Log.e("yzy", "inject: 注解id失败" + vField.getClass().getName() + ":" + vField.getName());
                }
            }
        }


        //获取所有的方法注解
        Method[] vMethods = pActivity.getClass().getDeclaredMethods();
        for (final Method vMethod : vMethods) {
            if (vMethod.isAnnotationPresent(Click.class)) {
                Click vAnnotation = vMethod.getAnnotation(Click.class);
                vMethod.setAccessible(true);
                    int[] ids = vAnnotation.value();
                    for (int vId : ids) {
                        pActivity.findViewById(vId).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View pView) {
                                try {
                                    vMethod.invoke(pActivity, pView);
                                }  catch (IllegalArgumentException pE){
                                    try {
                                        vMethod.invoke(pActivity);
                                    } catch (Exception pE1) {
                                        pE1.printStackTrace();
                                    }
                                } catch (InvocationTargetException pE) {
                                    pE.printStackTrace();
                                } catch (IllegalAccessException pE) {
                                    pE.printStackTrace();
                                }
                            }
                        });
                    }
            }
        }

    }
}
