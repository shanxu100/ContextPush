package edu.scut.luluteam.ubclibrary.collection.presenter;

import com.amap.api.location.AMapLocation;

/**
 * @author Guan
 * @date Created on 2018/4/2
 */
public interface IOnLocationPresenter {


    /**
     * 定位成功后，这里回调
     *
     * @param location
     */
    void onLocationInfo(AMapLocation location);


    /**
     * 成功加载设定的 地理围栏 后，调用这里
     */
    void onFinishedLoadGeoFenceInfo();

    void onStatsAppUsage();

}
