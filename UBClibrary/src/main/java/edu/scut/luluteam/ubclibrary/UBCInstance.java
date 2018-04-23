package edu.scut.luluteam.ubclibrary;

import android.app.Application;
import android.provider.Settings;

import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * @author Guan
 * @date Created on 2018/4/2
 */
public class UBCInstance {

    private static UBCInstance instance;
    private UBCNative ubcNative;

    private UBCInstance(Application context) {
        AppHolder.appContext = context;
        /**
         * 获取AndroidId，用于标识设备
         */
        AppHolder.androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        ubcNative = new UBCNative();
    }


    //============================

    /**
     * SDK 的入口
     *
     * @param context
     */
    public static void start(Application context) {
        getInstance(context).start();
    }

    public static UBCInstance getInstance(Application context) {
        if (instance == null) {
            synchronized (UBCInstance.class) {
                if (instance == null) {
                    instance = new UBCInstance(context);
                }
            }
        }
        return instance;
    }

    //============================

    /**
     * 真正开始启动SDK的相关程序
     */
    public void start() {
        ubcNative.start();
    }

}
