package edu.scut.luluteam.ubclibrary.collection.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.amap.api.location.AMapLocation;

import edu.scut.luluteam.ubclibrary.collection.model.UBCGeoFence;
import edu.scut.luluteam.ubclibrary.collection.model.UBCLocation;
import edu.scut.luluteam.ubclibrary.collection.model.UBCUsageStats;
import edu.scut.luluteam.ubclibrary.collection.presenter.IOnLocationPresenter;
import edu.scut.luluteam.ubclibrary.collection.view.IGeoFenceView;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * @author Guan
 * @date Created on 2018/4/2
 */
public class OnLocationOnLocationPresenter implements IOnLocationPresenter {

    private UBCGeoFence geoFence;

    private IGeoFenceView view;
    private GeoFenceHandler geoFenceHandler;
    private UBCLocation ubcLocation;

    private static OnLocationOnLocationPresenter mOnLocationPresenter;

    public static OnLocationOnLocationPresenter getInstance(IGeoFenceView view)
    {
        if (mOnLocationPresenter ==null)
        {
            synchronized (OnLocationOnLocationPresenter.class)
            {
                if (mOnLocationPresenter ==null)
                {
                    mOnLocationPresenter =new OnLocationOnLocationPresenter(view);
                }
            }
        }
        return mOnLocationPresenter;
    }



    private OnLocationOnLocationPresenter(IGeoFenceView view) {
        this.view = view;
        geoFenceHandler = new GeoFenceHandler(this.view);
        geoFence = new UBCGeoFence(this);
        ubcLocation = new UBCLocation(this);
    }

    public void startLocation() {
        ubcLocation.start();
    }

    public void stopLocation() {
        ubcLocation.stop();
    }

    public void startGeoFence() {
        geoFence.start();
    }

    public void stopGeoFence() {
        geoFence.stop();
    }

    public void statsAppUsage()
    {
        UBCUsageStats usageStats=new UBCUsageStats(AppHolder.appContext);
        usageStats.appStats();
    }

    @Override
    public void onLocationInfo(AMapLocation location) {
        String result = getLocationStr(location);
        System.out.println(result);
    }

    @Override
    public void onFinishedLoadGeoFenceInfo() {
        /**
         * 通知service，说已经成功加载 预设的地理围栏
         */
        geoFenceHandler.sendEmptyMessage(0);
    }

    @Override
    public void onStatsAppUsage() {

    }

    public synchronized static String getLocationStr(AMapLocation location) {
        if (null == location) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
        if (location.getErrorCode() == 0) {
            sb.append("定位成功" + "\n");
            sb.append("定位类型: " + location.getLocationType() + "\n");
            sb.append("经    度    : " + location.getLongitude() + "\n");
            sb.append("纬    度    : " + location.getLatitude() + "\n");
            sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
            sb.append("提供者    : " + location.getProvider() + "\n");

            sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
            sb.append("角    度    : " + location.getBearing() + "\n");
            // 获取当前提供定位服务的卫星个数
            sb.append("星    数    : " + location.getSatellites() + "\n");
            sb.append("国    家    : " + location.getCountry() + "\n");
            sb.append("省            : " + location.getProvince() + "\n");
            sb.append("市            : " + location.getCity() + "\n");
            sb.append("城市编码 : " + location.getCityCode() + "\n");
            sb.append("区            : " + location.getDistrict() + "\n");
            sb.append("区域 码   : " + location.getAdCode() + "\n");
            sb.append("地    址    : " + location.getAddress() + "\n");
            sb.append("兴趣点    : " + location.getPoiName() + "\n");
            //定位完成的时间
            //sb.append("定位时间: " + formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
        } else {
            //定位失败
            sb.append("定位失败" + "\n");
            sb.append("错误码:" + location.getErrorCode() + "\n");
            sb.append("错误信息:" + location.getErrorInfo() + "\n");
            sb.append("错误描述:" + location.getLocationDetail() + "\n");
        }
        //定位之后的回调时间
        //sb.append("回调时间: " + formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
        return sb.toString();
    }


    public static final class GeoFenceHandler extends Handler {
        private IGeoFenceView view;

        public GeoFenceHandler(IGeoFenceView view) {
            super();
            this.view = view;
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                view.onFinishedLoadGeoFence();
            }
        }
    }
}
