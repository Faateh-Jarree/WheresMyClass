package com.droids.ffs.smd_project.ViewWeeklySchedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.droids.ffs.smd_project.MainActivity;
import com.droids.ffs.smd_project.R;

public class SetReminderTime extends AppCompatActivity {


    private Button saveBtn;
    private NumberPicker alarmTimePicker;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.runFullScreenMode(this);
        setContentView(R.layout.set_reminder_time);

        saveBtn = (Button) findViewById(R.id.save_btn);
        alarmTimePicker = (NumberPicker) findViewById(R.id.number_picker_wgt);
        alarmTimePicker.setMinValue(0);
        alarmTimePicker.setMaxValue(60);
        alarmTimePicker.setWrapSelectorWheel(true);

    }



    public void onClickSave(View view) {

        int reminderTime = alarmTimePicker.getValue();





    }
}
