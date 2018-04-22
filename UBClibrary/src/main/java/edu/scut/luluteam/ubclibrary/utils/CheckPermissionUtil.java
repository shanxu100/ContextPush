package edu.scut.luluteam.ubclibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * @author Guan
 * @date Created on 2018/4/20
 */
public class CheckPermissionUtil {
    private static final String TAG = "CheckPermissionUtil";


    public static boolean checkPermission(Context context, String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                permission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "未授予权限permission:" + permission);
            return false;
        } else {
            Log.e(TAG, "已经授予权限permission:" + permission);
            return true;
        }
    }
}
