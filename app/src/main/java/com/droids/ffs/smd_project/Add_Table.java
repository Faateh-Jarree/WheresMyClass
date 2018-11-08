package com.droids.ffs.smd_project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Add_Table extends AppCompatActivity {

    Button addTable, selectCourses, viewSchedule;
    NumberPicker alarmTimePicker;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Buttons from the add_table layout
        addTable = (Button) findViewById(R.id.add_time_table_btn);
        selectCourses = (Button) findViewById(R.id.select_courses_btn);
        viewSchedule = (Button) findViewById(R.id.view_schedule_btn);

        //Alarm time Scroller picker
        alarmTimePicker = (NumberPicker) findViewById(R.id.number_picker_wgt);
        alarmTimePicker.setMinValue(1);
        alarmTimePicker.setMaxValue(60);
        alarmTimePicker.setWrapSelectorWheel(true);
    }



    protected void onClickAddTable(View view){
        Intent filepicker = new Intent(Intent.ACTION_GET_CONTENT);
        filepicker.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        startActivityForResult(filepicker, 1);
//        startActivity(filepicker);
    }

    protected void onClickSelectCourses(View view){

    }

    protected void onClickViewSchedule(View view){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Uri datas = data.getData();
                    String pdfPath = datas.getPath();
                    copyFile(pdfPath);
            }
        }
    }

    public void copyFile(String path) {
//        String sourcePath = Environment.getExternalStorageDirectory().getAbsolutePath() ;
//        Log.d("Jarrees", sourcePath);
        String sourcePath = path;
        File source = new File(sourcePath);

//        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Log.d("Jarree", destinationPath);
        String destinationPath = "/document/raw:/storage/emulated/0/Download/jarree";
        File destination = new File(destinationPath);
        try
        {
            FileUtils.copyFile(source, destination);
        }
        catch (IOException e)
        {
            Log.d("Jarree", "No can do!!!");
            e.printStackTrace();
        }
    }
}
