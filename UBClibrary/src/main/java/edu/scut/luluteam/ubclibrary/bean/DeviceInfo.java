package edu.scut.luluteam.ubclibrary.bean;

import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * @author Guan
 * @date Created on 2018/4/23
 */
public class DeviceInfo extends BaseInfo {
    //Android 唯一标致 Id
    protected String androidId;

    //目前电量
    private float batteryN;
    //电池温度
    private float batteryT;

    //手机型号
    private String model;
    //sdk版本
    private String sdkVersion;
    //系统版本
    private String releaseVersion;

    //网络类型
    private String networkType;


    /**
     * 构造函数
     */
    public DeviceInfo() {
        //必须设置AndroidId
        this.androidId = AppHolder.androidId;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public float getBatteryN() {
        return batteryN;
    }

    public void setBatteryN(float batteryN) {
        this.batteryN = batteryN;
    }

    public float getBatteryT() {
        return batteryT;
    }

    public void setBatteryT(float batteryT) {
        this.batteryT = batteryT;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }
}
