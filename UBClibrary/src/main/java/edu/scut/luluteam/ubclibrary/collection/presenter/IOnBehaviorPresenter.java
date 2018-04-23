package edu.scut.luluteam.ubclibrary.collection.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;

import edu.scut.luluteam.ubclibrary.collection.view.impl.BehaviorInfoService;

/**
 * @author Guan
 * @date Created on 2018/4/20
 */
public interface IOnBehaviorPresenter {
    /**
     * 开始统计App的使用时间等情况
     */
    void startAppStats();

    void startDeviceStats(Context context, BroadcastReceiver receiver,String[] actions);

    void stopDeviceStats(Context context, BroadcastReceiver receiver);


    void onBatteryInfo(float batteryN, float batteryT);

}
