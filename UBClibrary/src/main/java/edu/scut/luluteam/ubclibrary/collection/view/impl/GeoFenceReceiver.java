package edu.scut.luluteam.ubclibrary.collection.view.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.fence.GeoFence;

/**
 * @author Guan
 */
public class GeoFenceReceiver extends BroadcastReceiver {
    private static final String TAG = "GeoFenceReceiver";

    //定义接收广播的action字符串
    public static final String GEOFENCE_BROADCAST_ACTION = "edu.scut.luluteam.ubclibrary.collection.view.impl.GeoFenceService";


    @Override
    public void onReceive(Context context, Intent intent) {
        //获取Bundle
        Bundle bundle = intent.getExtras();
        //获取围栏行为：
        int status = bundle.getInt(GeoFence.BUNDLE_KEY_FENCESTATUS);
        //获取自定义的围栏标识：
        String customId = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
        //获取围栏ID:
        String fenceId = bundle.getString(GeoFence.BUNDLE_KEY_FENCEID);
        //获取当前有触发的围栏对象：
        GeoFence fence = bundle.getParcelable(GeoFence.BUNDLE_KEY_FENCE);

        Log.i(TAG,"触发围栏："+status + customId + fenceId + fence);
    }
}
