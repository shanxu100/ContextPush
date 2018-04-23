package edu.scut.luluteam.ubclibrary.collection.model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import edu.scut.luluteam.ubclibrary.bean.DeviceInfo;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;
import edu.scut.luluteam.ubclibrary.utils.PermisssionUtil;

/**
 * 获取设备信息
 *
 * @author Guan
 * @date Created on 2018/4/23
 */
public class UBCDeviceStats {

    private static final String TAG = "UBCDeviceStats";


    /**
     * 目前电量
     */
    public static float batteryN = -1;

    /**
     * 电池温度
     */
    public static float batteryT = -1;

    private Context context;


    public UBCDeviceStats(Context context) {
        this.context = context;
    }

    /**
     * 处理获取到的电池电量数据后，封装 android系统信息
     *
     * @param batteryN
     * @param batteryT
     */
    public void onBatteryInfo(float batteryN, float batteryT) {
        UBCDeviceStats.batteryN = batteryN;
        UBCDeviceStats.batteryT = batteryT;
    }

    /**
     * 获取当前网络状态
     *
     * @param context
     * @return
     */
    public static String getNetworkType(Context context) {
        String strNetworkType = "";

        if (!PermisssionUtil.checkPermission(context, Manifest.permission.ACCESS_NETWORK_STATE)) {
            return strNetworkType;
        }
        @SuppressLint("MissingPermission")
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String strSubTypeName = networkInfo.getSubtypeName();
                Log.e(TAG, "Network getSubtypeName : " + strSubTypeName);
                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = strSubTypeName;
                        }
                        break;
                }
                Log.e(TAG, "Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        }
        Log.e(TAG, "Network Type : " + strNetworkType);
        return strNetworkType;
    }


    /**
     * 获取所记录的当前设备的信息
     *
     * @param context
     * @return
     */
    public static DeviceInfo getDeviceInfo(Context context) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setBatteryN(batteryN);
        deviceInfo.setBatteryT(batteryT);
        deviceInfo.setModel(android.os.Build.MODEL);
        deviceInfo.setReleaseVersion(Build.VERSION.RELEASE);
        deviceInfo.setSdkVersion(Build.VERSION.SDK_INT + "");
        deviceInfo.setNetworkType(getNetworkType(context));
        Log.e(TAG, "统计设备信息：" + deviceInfo.toJson());
        return deviceInfo;
    }

}
