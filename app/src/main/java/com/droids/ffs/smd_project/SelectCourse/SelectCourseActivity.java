package com.droids.ffs.smd_project.SelectCourse;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.droids.ffs.smd_project.Notifications.AlarmReceiver;
import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.SQLite.Class;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SelectCourseActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener{


    private RecyclerView recyclerView;
    private static List<Class> classList;
    private List<Class> acceptedList;
    private CardListAdapter adapter;
    private CoordinatorLayout rootlayout;
    private Button doneButton;

    private  static Class acceptedItem;
    private static int acceptedIndex;
    DBHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_course);

        doneButton = (Button)findViewById(R.id.done);
        acceptedList = new ArrayList<>();
        db = new DBHandler(this);


        Intent i = getIntent();
        classList = (ArrayList<Class>) i
                .getSerializableExtra("classList");

        //Sets select course view
        SelectCoursesUI();
    }

    public void SelectCoursesUI(){

//        Sets select course UI
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rootlayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

//        classList = new ArrayList<>();
        adapter = new CardListAdapter(this, classList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemtouchhelperCallback = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(itemtouchhelperCallback).attachToRecyclerView(recyclerView);

        //TESTINGGG DUMMY DATA
        //Data from timetable
//        classList.add(new Class("Software for mobile devices","B","Monday","8:50","11:00","20","203",R.raw.fastlogo));
//        classList.add(new Class("Human Resources","F","Monday","2:00","3:00","10","202",R.raw.fastlogo));
//        classList.add(new Class("Artificial Intelligence","F","Tuesday","9:50","11:50","15","303",R.raw.fastlogo));
//        classList.add(new Class("Natural language Processing","E","Wednesday","8:50","11:00","20","316",R.raw.fastlogo));
//        classList.add(new Class("Deep Learning","B","Thursday","9:00","11:00","20","201",R.raw.fastlogo));
//        classList.add(new Class("Leadership","E","Friday","8:50","11:00","20","216",R.raw.fastlogo));

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        //On swipe, course is deleting

        if(viewHolder instanceof CardListAdapter.MyViewHolder){
//            String name = classList.get(viewHolder.getAdapterPosition()).getCourseName();

            acceptedItem = classList.get(position);
            acceptedIndex = position;
            adapter.removeItem(acceptedIndex);

            //adds accepted courses to list
            acceptedList.add(acceptedItem);

            //Shows UNDO bar at the bottom of the screen
            Snackbar snackbar = Snackbar.make(rootlayout,acceptedItem.getCourseName() + " selected from Timetable ",Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO",new View.OnClickListener(){

                //On clicking Undo, restore the deleted item
                @Override
                public void onClick(View view)
                {
                    acceptedList.remove(acceptedIndex);
                    adapter.restoreItem(acceptedItem,acceptedIndex);
                }
            });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    public void onClickDone(View view){

        Log.v("Functions","onClickDone");

        for(int i=0; i<acceptedList.size(); i++)
        {
            Log.d("ADDING", acceptedList.get(i).getCourseName());
            db.addClass(acceptedList.get(i));

            int h = Integer.valueOf(acceptedList.get(i).getClassStartTime().split(":")[0]);
            int m = Integer.valueOf(acceptedList.get(i).getClassStartTime().split(":")[1]);

            Log.v("TimeLog", String.valueOf(h)+":"+String.valueOf(m));

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("className", acceptedList.get(i).getCourseName());
            intent.putExtra("classroom", acceptedList.get(i).getClassRoom());
            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            if (h<8){
                h+=12;
            }
            calendar.set(Calendar.HOUR_OF_DAY, h);
            calendar.set(Calendar.MINUTE, m);
            calendar.set(Calendar.SECOND, 0);

            //Repeat after each week
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 7 * AlarmManager.INTERVAL_DAY, alarmIntent);


            Log.v("AlarmLog","Alarm set for "+acceptedList.get(i).getCourseName()+"#"+calendar.getTime());



        }

        Intent i = new Intent(this,ViewScheduleActivity.class);
        startActivity(i);

    }


}
