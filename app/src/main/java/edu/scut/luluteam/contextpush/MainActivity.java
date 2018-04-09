package edu.scut.luluteam.contextpush;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.scut.luluteam.ubclibrary.UBCInstance;
import edu.scut.luluteam.ubclibrary.collection.view.impl.GeoFenceService;


public class MainActivity extends AppCompatActivity {


    private Button test1_btn, test2_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", Looper.getMainLooper().getThread().getName() + " " + Looper.getMainLooper().getThread().getId() + " " + Looper.getMainLooper().getThread().toString());
        test1_btn = (Button) findViewById(R.id.test1_btn);
        test2_btn = (Button) findViewById(R.id.test2_btn);
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
}
