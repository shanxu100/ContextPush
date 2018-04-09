package edu.scut.luluteam.ubclibrary.collection.model;

import android.util.Log;

import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceClient;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.DPoint;

import java.util.List;

import edu.scut.luluteam.ubclibrary.collection.presenter.IPresenter;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;

import static com.amap.api.fence.GeoFenceClient.GEOFENCE_IN;
import static com.amap.api.fence.GeoFenceClient.GEOFENCE_OUT;
import static com.amap.api.fence.GeoFenceClient.GEOFENCE_STAYED;
import static edu.scut.luluteam.ubclibrary.collection.view.impl.GeoFenceReceiver.GEOFENCE_BROADCAST_ACTION;

/**
 * 判断 位置 与 地理围栏 的关系
 *
 * @author Guan
 * @date Created on 2018/4/9
 */
public class UBCGeoFence {

    private GeoFenceClient mGeoFenceClient;
    private IPresenter presenter;


    private static final String TAG = "UBCGeoFence";


    public UBCGeoFence(IPresenter presenter) {
        mGeoFenceClient = new GeoFenceClient(AppHolder.appContext);
        this.presenter = presenter;


        /**
         * 设置希望侦测的围栏触发行为，默认只侦测用户进入围栏的行为
         * public static final int GEOFENCE_IN 进入地理围栏
         * public static final int GEOFENCE_OUT 退出地理围栏
         * public static final int GEOFENCE_STAYED 停留在地理围栏内10分钟
         */
        mGeoFenceClient.setActivateAction(GEOFENCE_IN | GEOFENCE_OUT | GEOFENCE_STAYED);

        //设置回调监听,判断是否成功创建围栏
        mGeoFenceClient.setGeoFenceListener(new GeoFenceListener() {
            @Override
            public void onGeoFenceCreateFinished(List<GeoFence> list, int i, String s) {
                Log.i(TAG, "i:" + i + "\t自有业务Id:" + s);
                for (GeoFence geoFence : list) {
                    Log.i(TAG, "当前围栏：" + geoFence.toString());
                }
            }
        });
        //创建并设置PendingIntent
        mGeoFenceClient.createPendingIntent(GEOFENCE_BROADCAST_ACTION);

    }

    public void start() {
        loadGeoFence();
    }

    public void stop() {
        //会清除所有围栏
        mGeoFenceClient.removeGeoFence();
    }

    /**
     * 通过网络获取已经设置并保存的地理围栏数据
     */
    private void loadGeoFence() {

        //创建一个中心点坐标
        DPoint centerPoint = new DPoint();
        //设置中心点纬度
        centerPoint.setLatitude(23.046235D);
        //设置中心点经度
        centerPoint.setLongitude(113.40896D);
        /**
         * 半径单位，米
         */
        mGeoFenceClient.addGeoFence(centerPoint, 50f, "000111");

        presenter.onFinishedLoadGeoFenceInfo();

    }


}
