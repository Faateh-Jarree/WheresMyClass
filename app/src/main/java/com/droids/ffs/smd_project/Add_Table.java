package com.droids.ffs.smd_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Add_Table extends AppCompatActivity {

    Button addTable, selectCourses, viewSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.add_table);

        //Buttons from the add_table layout
        Button addTable = (Button) findViewById(R.id.add_time_table_btn);
        Button selectCourses = (Button) findViewById(R.id.select_courses_btn);
        Button viewSchedule = (Button) findViewById(R.id.view_schedule_btn);

    }

    protected void onClickAddTable(View view){
        Intent filepicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        filepicker.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        startActivity(filepicker);
    }

    protected void onClickSelectCourses(View view){

    }

    protected void onClickViewSchedule(View view){

    }
}
