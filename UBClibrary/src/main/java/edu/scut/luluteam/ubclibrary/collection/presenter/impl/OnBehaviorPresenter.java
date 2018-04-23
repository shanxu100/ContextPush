package edu.scut.luluteam.ubclibrary.collection.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import edu.scut.luluteam.ubclibrary.collection.model.UBCAppUsageStats;
import edu.scut.luluteam.ubclibrary.collection.model.UBCDeviceStats;
import edu.scut.luluteam.ubclibrary.collection.presenter.IOnBehaviorPresenter;
import edu.scut.luluteam.ubclibrary.collection.view.impl.BehaviorInfoService;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * @author Guan
 * @date Created on 2018/4/20
 */
public class OnBehaviorPresenter implements IOnBehaviorPresenter {

    private UBCAppUsageStats usageStats;
    private UBCDeviceStats deviceStats;


    private static OnBehaviorPresenter onBehaviorPresenter;

    public static OnBehaviorPresenter getInstance() {
        if (onBehaviorPresenter == null) {
            synchronized (OnBehaviorPresenter.class) {
                if (onBehaviorPresenter == null) {
                    onBehaviorPresenter = new OnBehaviorPresenter();
                }
            }
        }
        return onBehaviorPresenter;
    }

    private OnBehaviorPresenter() {
        usageStats = new UBCAppUsageStats(AppHolder.appContext);
        deviceStats = new UBCDeviceStats(AppHolder.appContext);

    }


    @Override
    public void startAppStats() {
        usageStats.appStats();
    }

    @Override
    public void startDeviceStats(Context context, BroadcastReceiver receiver,String[] actions) {

        IntentFilter intentFilter = new IntentFilter();
        for (String action:actions)
        {
            intentFilter.addAction(action);
        }
        context.registerReceiver(receiver, intentFilter);
    }

    @Override
    public void stopDeviceStats(Context context, BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }

    @Override
    public void onBatteryInfo(float batteryN, float batteryT) {
        deviceStats.onBatteryInfo(batteryN, batteryT);
    }


}
