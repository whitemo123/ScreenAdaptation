package com.mobai.screentest;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtils {

    Context context;

    //设置标准的屏幕宽度和高度
    private static float correctly_width = 1080F;
    private static float correctly_height = 1920F;

    //系统存放相关配置信息的地方 这里用来获取状态栏高度
    private static String AndroidClass = "com.android.internal.R$dimen";

    //实际Android的屏幕宽高
    private static float actual_width;
    private static float actual_height;

    //水平和垂直的缩放比
    private static float HorizontalScale;
    private static float VerticalScale;

    public float getHorizontalScale() {
        return this.HorizontalScale;
    }

    public float getVerticalScale() {
        return this.VerticalScale;
    }

    public UIUtils(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (actual_width == 0.0F || actual_height == 0.0F) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }

        //获取状态栏的高度
        int systemBarHeight = getSystemBarHeight(context);

        //适配平板和手机
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            //平板
            this.actual_width = displayMetrics.heightPixels;
            this.actual_height = displayMetrics.widthPixels - systemBarHeight;
        } else {
            //手机
            this.actual_width = displayMetrics.widthPixels;
            this.actual_height = displayMetrics.heightPixels - systemBarHeight;
        }

        this.HorizontalScale = ((float) this.actual_width) / correctly_width;
        this.VerticalScale = ((float) this.actual_height) / correctly_height;
    }
    /**
     * 通过反射获取状态栏高度
     * @param context
     * @return
     */
    private int getSystemBarHeight(Context context) {
        try {
            Class c = Class.forName(AndroidClass);
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int h = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(h);
        } catch (Exception e) {
            e.printStackTrace();
            return 48;
        }
    }
}