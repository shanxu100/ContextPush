package edu.scut.luluteam.ubclibrary.collection.view;

/**
 * @author Guan
 * @date Created on 2018/4/9
 */
public interface IGeoFenceView {

    /**
     * 加载完设定的地理围栏后，注册广播监听器
     */
    void onFinishedLoadGeoFence();
}
