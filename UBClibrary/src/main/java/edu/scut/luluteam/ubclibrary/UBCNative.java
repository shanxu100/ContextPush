package edu.scut.luluteam.ubclibrary;

import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import edu.scut.luluteam.ubclibrary.collection.model.manager.LocationManagerTest;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * @author Guan
 * @date Created on 2018/4/3
 */
public class UBCNative {


    private static final String TAG = "UBCNative";

    private LocationManagerTest locationManagerTest;

    public UBCNative() {

    }

    public void start() {
        if (!checkRunOnMainThread() || !checkPermission()) {
            return;
        }

//        Intent intent = new Intent(AppHolder.appContext, MyIntentService.class);
//        (AppHolder.appContext).startService(intent);

        locationManagerTest = new LocationManagerTest();
        locationManagerTest.start();
//        Log.e(TAG,AppHolder.appContext.getPackageName());

    }



    /**
     * 检查是否运行在主线程
     *
     * @return
     */
    private boolean checkRunOnMainThread() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }


    /**
     * 检查权限是否成功赋予
     */
    private boolean checkPermission() {
        for (String permission : AppHolder.PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(AppHolder.appContext, permission) != PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "未授予相关权限，UBC SDK运行终止: " + permission);
                return false;
            }
        }
        return true;
    }

}
