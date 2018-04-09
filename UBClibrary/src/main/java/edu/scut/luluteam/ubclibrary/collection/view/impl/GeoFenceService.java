package edu.scut.luluteam.ubclibrary.collection.view.impl;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;

import edu.scut.luluteam.ubclibrary.collection.presenter.impl.Presenter;
import edu.scut.luluteam.ubclibrary.collection.view.IGeoFenceView;

import static edu.scut.luluteam.ubclibrary.collection.view.impl.GeoFenceReceiver.GEOFENCE_BROADCAST_ACTION;

/**
 * @author Guan
 */
public class GeoFenceService extends Service implements IGeoFenceView {


    private GeoFenceReceiver mGeoFenceReceiver;

    private Presenter presenter;

    @Override
    public void onCreate() {
        super.onCreate();
        presenter = new Presenter(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        presenter.startLocation();
        presenter.startGeoFence();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        presenter.stopLocation();
        presenter.stopGeoFence();
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
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(GEOFENCE_BROADCAST_ACTION);
        registerReceiver(mGeoFenceReceiver, filter);
    }


}
