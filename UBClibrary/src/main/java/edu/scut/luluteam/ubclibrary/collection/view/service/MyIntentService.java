package edu.scut.luluteam.ubclibrary.collection.view.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import edu.scut.luluteam.ubclibrary.constant.AppHolder;
import edu.scut.luluteam.ubclibrary.utils.ToastUtil;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
//        ToastUtil.showLongToast(AppHolder.appContext,"MyIntentService======");
        Log.e("myIntentService","MyIntentService======");
    }

}
