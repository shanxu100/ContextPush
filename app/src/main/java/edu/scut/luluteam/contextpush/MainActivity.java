package edu.scut.luluteam.contextpush;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

        String[] permissions = new String[]{Manifest.permission.PACKAGE_USAGE_STATS};
        ActivityCompat.requestPermissions(this, permissions, 1);

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
                Log.i(TAG,permissionStr+" 申请成功");
            }else {
                Log.i(TAG,permissionStr+" 申请失败");
            }
        }

    }
}
