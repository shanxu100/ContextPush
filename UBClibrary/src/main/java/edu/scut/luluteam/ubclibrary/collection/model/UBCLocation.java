package edu.scut.luluteam.ubclibrary.collection.model;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import edu.scut.luluteam.ubclibrary.collection.presenter.IOnLocationPresenter;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * 收集位置信息
 *
 * @author Guan
 * @date Created on 2018/4/8
 */
public class UBCLocation {
    private AMapLocationClient locationClient;
    private AMapLocationListener locationListener;

    private static final String TAG = "UBCLocation";

    private IOnLocationPresenter presenter;

    public UBCLocation(final IOnLocationPresenter presenter) {
        this.presenter = presenter;
    }


    public void start() {
        Log.i(TAG, "开始定位");
        locationClient = new AMapLocationClient(AppHolder.appContext);
        locationClient.setLocationOption(getOption());
        locationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    presenter.onLocationInfo(aMapLocation);
                    locationClient.stopLocation();
                    Log.i(TAG, "停止定位");
                } else {
                    Log.e(TAG, "定位失败......");
                }

            }
        };
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
    }

    public void stop() {
        Log.i(TAG, "结束定位");

        if (null != locationClient) {
            //销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
            locationClient.onDestroy();
            locationClient.unRegisterLocationListener(locationListener);
            locationClient = null;
            locationListener = null;
        }
    }

    /**
     * 配置 定位参数
     *
     * @return
     */
    private AMapLocationClientOption getOption() {
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //获取一次定位结果：
        // 该方法默认为false。
//        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        // 设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        return mLocationOption;
    }


}
