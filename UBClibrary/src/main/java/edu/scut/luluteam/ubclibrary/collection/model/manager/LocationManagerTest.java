package edu.scut.luluteam.ubclibrary.collection.model.manager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;

import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * @author Guan
 * @date Created on 2018/4/8
 */
public class LocationManagerTest {
    private AMapLocationClient locationClient;

    public LocationManagerTest() {
        locationClient = new AMapLocationClient(AppHolder.appContext);
    }

    public void start() {
        AMapLocation location = locationClient.getLastKnownLocation();
        String result = getLocationStr(location);
        System.out.println(result);
    }

    public void stop() {
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
        }
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
}
