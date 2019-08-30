package com.twy.input.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.twy.input.base.BaseApplication;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/8/30.
 * PS: Not easy to write code, please indicate.
 */
public class UIUtils {
    /**
     * 上下文的获取
     *
     * @return
     */
    public static Context getContext(){
        return BaseApplication.instance;
    }

    /**
     * 获取资源
     * @return
     */
    public static Resources getResources(){
        return BaseApplication.instance.getResources();
    }

    /**
     *
     * @param dip
     * @return
     */
    public static int dip2px(int dip){
        // 公式 1: px = dp * (dpi / 160)
        // 公式 2: dp = px / denistity;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        // metrics.densityDpi
        return (int) (dip * density + 0.5f);
    }
    public static int dip2px(float dip){
        // 公式 1: px = dp * (dpi / 160)
        // 公式 2: dp = px / denistity;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        // metrics.densityDpi
        return (int) (dip * density + 0.5f);
    }

    public static int px2dip(int px){
        // 公式 1: px = dp * (dpi / 160)
        // 公式 2: dp = px / denistity;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        // metrics.densityDpi
        return (int) (px / density + 0.5f);
    }

    public static int sp2px(int sp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float fontScale = metrics.scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float fontScale = metrics.scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /** 获取文字 */
    public static String getString(int resId){
        return getResources().getString(resId);
    }

    /**
     * 获得应用程序的包名
     * @return
     */
    public static String getPackageName(){
        return BaseApplication.instance.getPackageName();
    }

    /** 获取文字数组 */
    public static String[] getStringArray(int resId){
        return getResources().getStringArray(resId);
    }

    /** 获取颜色 */
    public static int getColor(int resId){
        return getResources().getColor(resId);
    }

    public static View inflate(int resId){
        return LayoutInflater.from(BaseApplication.instance).inflate(resId, null);
    }

    public static void getToastShort(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void getToastLong(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        return dm.heightPixels;
    }

    //判断输入法是否显示
    public static boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }
}
