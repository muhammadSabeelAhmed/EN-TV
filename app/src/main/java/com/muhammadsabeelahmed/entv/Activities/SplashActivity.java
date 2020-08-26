package com.muhammadsabeelahmed.entv.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.muhammadsabeelahmed.entv.Global;
import com.muhammadsabeelahmed.entv.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Global.changeActivity(SplashActivity.this, new DashboardActivity());
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, 4000);
    }
}