package edu.scut.luluteam.ubclibrary;

import android.app.Application;

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
        ubcNative=new UBCNative();
    }


    //============================

    /**
     * SDK 的入口
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
    public void start()
    {
        ubcNative.start();
    }

}
