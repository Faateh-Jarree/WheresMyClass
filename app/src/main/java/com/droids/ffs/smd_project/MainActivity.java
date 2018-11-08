package com.droids.ffs.smd_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.droids.ffs.smd_project.SQLite.Class;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.SelectCourse.SelectCourseActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String PREFS_NAME = "MyPrefsFile";
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        //Running Fullscreen Mode
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        Intent i = new Intent(this,SelectCourseActivity.class);
//        startActivity(i);


        //FOR TESTING USING DUMMY DATA
//        DatabaseOperation();



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



//    public void DatabaseOperation(){
//        //TESTINGGG
//        db = new DBHandler(this);
//        Log.d("Insert","Inserting");
//
//        db.addClass(new Class("Software for mobile devices","B","Monday","8:50","11:00","20","203",R.raw.fastlogo));
//        db.addClass(new Class("Human Resources","F","Monday","2:00","3:00","10","202",R.raw.fastlogo));
//        db.addClass(new Class("Artificial Intelligence","F","Tuesday","9:50","11:50","15","303",R.raw.fastlogo));
//        db.addClass(new Class("Natural language Processing","E","Wednesday","8:50","11:00","20","316",R.raw.fastlogo));
//        db.addClass(new Class("Deep Learning","B","Thursday","9:00","11:00","20","201",R.raw.fastlogo));
//        db.addClass(new Class("Leadership","E","Friday","8:50","11:00","20","216",R.raw.fastlogo));
//
//        Log.d("Read","Reading");
//        List<Class> classes = db.getAllClasses();
//
//        for(int i=0;i<classes.size();i++){
//            Log.d("Reading",classes.get(i).getCourseName());
//        }
//    }
}
