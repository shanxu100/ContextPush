package edu.scut.luluteam.ubclibrary.constant;

import android.Manifest;
import android.content.Context;

/**
 * @author JJY
 * @date 2016/3/23
 */
public class AppHolder {
    public static Context appContext;
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
    };
}
