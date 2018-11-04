package com.droids.ffs.smd_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;

public class Add_Table extends AppCompatActivity {

    Button addTable, selectCourses, viewSchedule;
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.add_table);

        //Buttons from the add_table layout
        addTable = (Button) findViewById(R.id.add_time_table_btn);
        selectCourses = (Button) findViewById(R.id.select_courses_btn);
        viewSchedule = (Button) findViewById(R.id.view_schedule_btn);

        numberPicker = (NumberPicker) findViewById(R.id.number_picker_wgt);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(60);
        numberPicker.setWrapSelectorWheel(true);
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
