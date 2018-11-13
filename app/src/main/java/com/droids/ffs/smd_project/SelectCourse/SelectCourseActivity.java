package com.droids.ffs.smd_project.SelectCourse;

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

import com.droids.ffs.smd_project.Add_Table;
import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.SQLite.Class;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectCourseActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener{


    private RecyclerView recyclerView;
    private List<Class> classlist;
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

        classlist = new ArrayList<>();
        adapter = new CardListAdapter(this,classlist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemtouchhelperCallback = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(itemtouchhelperCallback).attachToRecyclerView(recyclerView);

        //TESTINGGG DUMMY DATA
        //Data from timetable
        classlist.add(new Class("Software for mobile devices","B","Monday","8:50","11:00","20","203",R.raw.fastlogo));
        classlist.add(new Class("Human Resources","F","Monday","2:00","3:00","10","202",R.raw.fastlogo));
        classlist.add(new Class("Artificial Intelligence","F","Tuesday","9:50","11:50","15","303",R.raw.fastlogo));
        classlist.add(new Class("Natural language Processing","E","Wednesday","8:50","11:00","20","316",R.raw.fastlogo));
        classlist.add(new Class("Deep Learning","B","Thursday","9:00","11:00","20","201",R.raw.fastlogo));
        classlist.add(new Class("Leadership","E","Friday","8:50","11:00","20","216",R.raw.fastlogo));

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        //On swipe, course is deleting

        if(viewHolder instanceof CardListAdapter.MyViewHolder){
//            String name = classlist.get(viewHolder.getAdapterPosition()).getCourseName();

            acceptedItem = classlist.get(position);
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

        for(int i=0; i<acceptedList.size(); i++)
        {
            Log.d("ADDING", acceptedList.get(i).getCourseName());
            db.addClass(acceptedList.get(i));
        }

        Intent i = new Intent(this,ViewScheduleActivity.class);
        startActivity(i);

    }


}
