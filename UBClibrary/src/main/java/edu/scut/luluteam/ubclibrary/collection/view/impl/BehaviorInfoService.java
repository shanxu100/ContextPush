package edu.scut.luluteam.ubclibrary.collection.view.impl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import edu.scut.luluteam.ubclibrary.collection.presenter.impl.OnBehaviorPresenter;

public class BehaviorInfoService extends Service {
    public BehaviorInfoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        OnBehaviorPresenter.getInstance().startStats();

        return super.onStartCommand(intent, flags, startId);
    }
}
