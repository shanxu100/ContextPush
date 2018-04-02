package edu.scut.luluteam.ubclibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by guan on 5/19/17.
 */

public class DisplayUtil {

    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;
    private static int DENSITY_DPI;
    private static int STATUS_BAR_HEIGHT;

    //但例模式：饿汉式
    private static DisplayUtil displayUtil = new DisplayUtil();

    private Context mContext;


    private static String TAG = "DisplayUtil";

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getDensityDpi() {
        return DENSITY_DPI;
    }

    public static int getStatusBarHeight() {
        return STATUS_BAR_HEIGHT;
    }


    //=================================================
    public static void init(Context mContext) {
        displayUtil.saveScreenInfo(mContext);
        displayUtil.saveStatusBarHeight(mContext);
    }


    /**
     * 获取并保存屏幕大小
     *
     * @param mContext
     */
    private void saveScreenInfo(Context mContext) {

        if (SCREEN_WIDTH != 0 && SCREEN_HEIGHT != 0) {
            return;
        }

        //通过Application获取屏幕信息
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);

        // 屏幕宽度（像素）
        SCREEN_WIDTH = metric.widthPixels;
        // 屏幕高度（像素）
        SCREEN_HEIGHT = metric.heightPixels;
        DENSITY_DPI = metric.densityDpi;

        Log.d(TAG, "SCREEN_WIDTH:\t" + SCREEN_WIDTH +
                "\tSCREEN_HEIGHT:\t" + SCREEN_HEIGHT +
                "\tDENSITY_DPI:\t" + DENSITY_DPI);
    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    private int saveStatusBarHeight(Context mContext) {
        if (STATUS_BAR_HEIGHT == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                STATUS_BAR_HEIGHT = mContext.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return STATUS_BAR_HEIGHT;
    }
}
