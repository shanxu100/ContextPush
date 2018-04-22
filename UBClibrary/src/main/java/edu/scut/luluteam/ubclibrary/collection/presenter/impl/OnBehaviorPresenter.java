package edu.scut.luluteam.ubclibrary.collection.presenter.impl;

import edu.scut.luluteam.ubclibrary.collection.model.UBCUsageStats;
import edu.scut.luluteam.ubclibrary.collection.presenter.IOnBehaviorPresenter;
import edu.scut.luluteam.ubclibrary.constant.AppHolder;

/**
 * @author Guan
 * @date Created on 2018/4/20
 */
public class OnBehaviorPresenter implements IOnBehaviorPresenter {

    private UBCUsageStats usageStats;


    private static OnBehaviorPresenter onBehaviorPresenter;

    public static OnBehaviorPresenter getInstance()
    {
        if (onBehaviorPresenter ==null)
        {
            synchronized (OnBehaviorPresenter.class)
            {
                if (onBehaviorPresenter ==null)
                {
                    onBehaviorPresenter =new OnBehaviorPresenter();
                }
            }
        }
        return onBehaviorPresenter;
    }
    private OnBehaviorPresenter()
    {
        usageStats=new UBCUsageStats(AppHolder.appContext);

    }


    @Override
    public void startStats() {
        usageStats.appStats();
    }
}
