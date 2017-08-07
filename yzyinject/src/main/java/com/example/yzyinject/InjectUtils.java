package com.example.yzyinject;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yzy on 2017/8/5.
 */


public class InjectUtils {
    /**
     * 注入Activity
     * @param pActivity
     */
    public static void injectActivity(final Activity pActivity) {
        if (pActivity.getClass().isAnnotationPresent(ContentView.class)) {
            ContentView vAnnotation = pActivity.getClass().getAnnotation(ContentView.class);
            int layout = vAnnotation.value();
            if (layout == -1) {
                Log.e("yzy", "布局layout没找到");
            }
            pActivity.setContentView(layout);
        }


        //获取所有的属性注解
        Field[] vFields = pActivity.getClass().getDeclaredFields();
        for (Field vField : vFields) {
            if (vField.isAnnotationPresent(ViewInject.class)) {
                ViewInject vAnnotation = vField.getAnnotation(ViewInject.class);
                int id = vAnnotation.value();//这个就是去除注解中的id值
                String vText = vAnnotation.text();
                if (id == -1) {
                    Log.e("yzy", "id没找到");
                }else {
                    vField.setAccessible(true);
                    View vViewById = pActivity.findViewById(id);
                    try {
                        vField.set(pActivity, vViewById);
                        if (!"".equals(vText)) {
                            Object obj = vField.get(pActivity);
                            if (obj instanceof TextView) {
                                ((TextView) obj).setText(vText);
                            }
                        }
                    } catch (IllegalAccessException pE) {
                        pE.printStackTrace();
                        Log.e("yzy", "injectActivity: 注入id失败" + vField.getClass().getName() + ":" + vField.getName());
                    } catch (ClassCastException pE) {
                        pE.printStackTrace();
                        Log.e("yzy", "injectActivity: 注入文字失败" + vField.getClass().getName() + ":" + vField.getName());
                    }
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
                            } catch (IllegalArgumentException pE) {
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

    /**
     * 注入Fragment
     * @param fragment
     * @return
     */
    public static View injectFragment(final Fragment fragment){
        Activity vActivity = fragment.getActivity();
        View view = null;
        if(fragment.getClass().isAnnotationPresent(ContentView.class)) {
            ContentView vAnnotation = fragment.getClass().getAnnotation(ContentView.class);
            int layout = vAnnotation.value();
            view = View.inflate(vActivity,layout,null);
        }

        if(view == null) {
            return view;
        }

        //获取所有的属性注解
        Field[] vFields = fragment.getClass().getDeclaredFields();
        for (Field vField : vFields) {
            if (vField.isAnnotationPresent(ViewInject.class)) {
                ViewInject vAnnotation = vField.getAnnotation(ViewInject.class);
                int id = vAnnotation.value();//这个就是去除注解中的id值
                String vText = vAnnotation.text();
                if (id == -1) {
                    Log.e("yzy", "id没找到");
                }else {
                    vField.setAccessible(true);
                    View vViewById = view.findViewById(id);
                    try {
                        vField.set(fragment, vViewById);
                        if (!"".equals(vText)) {
                            Object obj = vField.get(fragment);
                            if (obj instanceof TextView) {
                                ((TextView) obj).setText(vText);
                            }
                        }
                    } catch (IllegalAccessException pE) {
                        pE.printStackTrace();
                        Log.e("yzy", "injectActivity: 注入id失败" + vField.getClass().getName() + ":" + vField.getName());
                    } catch (ClassCastException pE) {
                        pE.printStackTrace();
                        Log.e("yzy", "injectActivity: 注入文字失败" + vField.getClass().getName() + ":" + vField.getName());
                    }
                }
            }
        }

        //获取所有的方法注解
        Method[] vMethods = fragment.getClass().getDeclaredMethods();
        for (final Method vMethod : vMethods) {
            if (vMethod.isAnnotationPresent(Click.class)) {
                Click vAnnotation = vMethod.getAnnotation(Click.class);
                vMethod.setAccessible(true);
                int[] ids = vAnnotation.value();
                for (int vId : ids) {
                    view.findViewById(vId).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View pView) {
                            try {
                                vMethod.invoke(fragment, pView);
                            } catch (IllegalArgumentException pE) {
                                try {
                                    vMethod.invoke(fragment);
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

        return view;
    }
}
