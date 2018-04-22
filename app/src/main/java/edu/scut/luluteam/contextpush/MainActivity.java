package edu.scut.luluteam.contextpush;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.scut.luluteam.ubclibrary.UBCInstance;
import edu.scut.luluteam.ubclibrary.collection.view.impl.GeoFenceService;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button test1_btn, test2_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", Looper.getMainLooper().getThread().getName() + " " + Looper.getMainLooper().getThread().getId() + " " + Looper.getMainLooper().getThread().toString());
        test1_btn = (Button) findViewById(R.id.test1_btn);
        test2_btn = (Button) findViewById(R.id.test2_btn);
//        PermissionActivity permissionActivity=new PermissionActivity();

//        String[] permissions = new String[]{Manifest.permission.PACKAGE_USAGE_STATS};
//        ActivityCompat.requestPermissions(this, permissions, 1);
        checkUsagePermission();

        test1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                LocationManager.getInstance(getApplication()).start();
                UBCInstance.start(getApplication());
//                Intent geoFenceIntent = new Intent(getApplicationContext(), GeoFenceService.class);
//                startService(geoFenceIntent);

            }
        });
        test2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LocationManager.getInstance(getApplication()).stop();

                Intent geoFenceIntent = new Intent(getApplicationContext(), GeoFenceService.class);
                stopService(geoFenceIntent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            String permissionStr = permissions[i];
            int grantResult = grantResults[i];
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, permissionStr + " 申请成功");
            } else {
                Log.i(TAG, permissionStr + " 申请失败");
            }
        }

    }

    private boolean checkUsagePermission() {
        //api是在19新加入的，所以要注意加个判断
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            int mode = 0;
            mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), getPackageName());
            boolean granted = mode == AppOpsManager.MODE_ALLOWED;
            if (!granted) {
                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                startActivityForResult(intent, 1);
                return false;
            }
        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            int mode = 0;
            mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), getPackageName());
            boolean granted = mode == AppOpsManager.MODE_ALLOWED;
            if (!granted) {
                Toast.makeText(this, "请开启该权限", Toast.LENGTH_SHORT).show();
            }else {
                System.out.println("申请成功");
            }
        }
    }
}

