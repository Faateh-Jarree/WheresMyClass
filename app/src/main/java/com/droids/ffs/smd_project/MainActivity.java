package com.droids.ffs.smd_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        //Running Fullscreen Mode
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //App used for the first time?
        if (appUsedForFirstTime()){

            //Running the splash screen
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, Add_Table.class);
                    startActivity(i);
                    finish();
                }
            },1200);
        }

        // Not using for the first time
        else{

            //Running the splash screen
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, Add_Table.class);
                    startActivity(i);
                    finish();
                }
            },1200);
        }


    }

    protected boolean appUsedForFirstTime() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("first_time_use?", true)) {
            //the app is being launched for first time, do something
            Log.d("First Time?", "Yes");

            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("first_time_use?", false).commit();
            return true;
        }
        Log.d("First Time?", "NO");
        return false;
    }
}
