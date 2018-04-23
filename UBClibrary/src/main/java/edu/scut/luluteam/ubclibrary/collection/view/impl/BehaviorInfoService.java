package edu.scut.luluteam.ubclibrary.collection.view.impl;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import edu.scut.luluteam.ubclibrary.collection.presenter.impl.OnBehaviorPresenter;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;

public class BehaviorInfoService extends Service {

    private DeviceInfoReceiver deviceInfoReceiver = new DeviceInfoReceiver();
    private static final String TAG = "BehaviorInfoService";

    public BehaviorInfoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OnBehaviorPresenter.getInstance()
                .startDeviceStats(AppHolder.appContext, deviceInfoReceiver,
                new String[]{Intent.ACTION_BATTERY_CHANGED,
                        Intent.ACTION_SCREEN_ON,
                        Intent.ACTION_SCREEN_OFF});
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        OnBehaviorPresenter.getInstance().startAppStats();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OnBehaviorPresenter.getInstance()
                .stopDeviceStats(AppHolder.appContext, deviceInfoReceiver);
    }

    /**
     * 监听电量、屏幕亮灭的事件
     */
    public static class DeviceInfoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "BroadcastReceiver:" + action);
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                //目前电量
                float batteryN = intent.getIntExtra("level", 0);
                //电池温度
                float batteryT = intent.getIntExtra("temperature", 0) / 10;
                OnBehaviorPresenter.getInstance().onBatteryInfo(batteryN, batteryT);
            } else if (Intent.ACTION_SCREEN_ON.equals(action) || Intent.ACTION_SCREEN_OFF.equals(action)) {
                Log.e(TAG, "屏幕电量或关闭：" + action);
            }
        }
    }

}
