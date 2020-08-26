package com.muhammadsabeelahmed.entv.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.muhammadsabeelahmed.entv.BuildConfig;
import com.muhammadsabeelahmed.entv.Fragments.HomeFragment;
import com.muhammadsabeelahmed.entv.Fragments.VideoFragment;
import com.muhammadsabeelahmed.entv.Global;
import com.muhammadsabeelahmed.entv.R;


public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String checkIcon = "true";
    public static TextView main_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        main_text = findViewById(R.id.txt_main);
        Global.changeFragmentHome(DashboardActivity.this, new HomeFragment());
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //  Drawable drawable = AppCompatResources.getDrawable(DashboardActivity.this, R.drawable.fb);
        final SpeedDialView speedDialView = findViewById(R.id.speedDial);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            speedDialView.addActionItem(
                    new SpeedDialActionItem.Builder(R.id.fab_share, R.drawable.share)
                            .setLabelColor(Color.WHITE)
                            .setFabBackgroundColor(getColor(R.color.dark_bg))
                            .create());

            speedDialView.addActionItem(
                    new SpeedDialActionItem.Builder(R.id.fab_insta, R.drawable.insta)
                            .setLabelColor(Color.WHITE)
                            .setFabBackgroundColor(getColor(R.color.dark_bg))
                            .create());

            speedDialView.addActionItem(
                    new SpeedDialActionItem.Builder(R.id.fab_twitter, R.drawable.twitter)
                            .setLabelColor(Color.WHITE)
                            .setFabBackgroundColor(getColor(R.color.dark_bg))
                            .create());

            speedDialView.addActionItem(
                    new SpeedDialActionItem.Builder(R.id.fab_youtube, R.drawable.youtube)
                            .setLabelColor(Color.WHITE)
                            .setFabBackgroundColor(getColor(R.color.dark_bg))
                            .create());

            speedDialView.addActionItem(
                    new SpeedDialActionItem.Builder(R.id.fab_fb, R.drawable.fb)
                            .setLabelColor(Color.WHITE)
                            .setFabBackgroundColor(getColor(R.color.dark_bg))
                            .create());
        }

        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem speedDialActionItem) {
                Log.d("SpeedDial", "Main before switch" + speedDialActionItem.getId());
                switch (speedDialActionItem.getId()) {
                    case R.id.speedDial:
                        Log.d("SpeedDial", "Main Clicked");
                        return false; // true to keep the Speed Dial open
                    case R.id.fab_fb:
                        goToUrl("https://www.facebook.com/");
                        return true;
                    case R.id.fab_youtube:
                        goToUrl("https://www.youtube.com/");
                        return true;
                    case R.id.fab_twitter:
                        goToUrl("https://twitter.com/");
                        return true;
                    case R.id.fab_insta:
                        goToUrl("https://www.instagram.com/");
                        return true;
                    case R.id.fab_share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "Hey check out EN TV at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        speedDialView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Global.changeFragmentHome(DashboardActivity.this, new HomeFragment());
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;
            case R.id.nav_notifications:
                break;
            case R.id.nav_contacts:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
            DashboardActivity.main_text.setText("Home");
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    protected void onPause() {
        Log.d("DashboardActivity", "onPause");
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
            DashboardActivity.main_text.setText("Home");
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("DashboardActivity", "onResume");
        if (Global.device_back_tag.equals("VideoFragment")) {
            Global.changeFragmentHome(DashboardActivity.this, new VideoFragment());
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        super.onResume();
    }
}