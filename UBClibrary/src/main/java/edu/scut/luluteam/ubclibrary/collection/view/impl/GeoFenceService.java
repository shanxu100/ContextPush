package edu.scut.luluteam.ubclibrary.collection.view.impl;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.fence.GeoFence;

import edu.scut.luluteam.ubclibrary.collection.presenter.impl.OnLocationOnLocationPresenter;
import edu.scut.luluteam.ubclibrary.collection.view.IGeoFenceView;



/**
 * @author Guan
 */
public class GeoFenceService extends Service implements IGeoFenceView {


    private GeoFenceReceiver mGeoFenceReceiver;

    private OnLocationOnLocationPresenter onLocationPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        onLocationPresenter = OnLocationOnLocationPresenter.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onLocationPresenter.startLocation();
        onLocationPresenter.startGeoFence();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        onLocationPresenter.stopLocation();
        onLocationPresenter.stopGeoFence();
        unregisterReceiver(mGeoFenceReceiver);
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onFinishedLoadGeoFence() {
        /**
         * 在成功加载 地理围栏 后，注册监听
         */
        mGeoFenceReceiver = new GeoFenceReceiver();
        // 2. 设置接收广播的类型
        //监听网络变化
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //监听地理围栏
        filter.addAction(GeoFenceReceiver.GEOFENCE_BROADCAST_ACTION);
        registerReceiver(mGeoFenceReceiver, filter);
    }

    /**
     * 接收 地理围栏事件 的receiver
     */
    public static class GeoFenceReceiver extends BroadcastReceiver {
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


}
