package com.droids.ffs.smd_project.ViewWeeklySchedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.Class;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import java.util.List;

public class TuesdayFragment extends Fragment {


    List<Class> classes;
    RecyclerView recyclerView;
    DBHandler db;
    ViewScheduleRecyclerViewAdapter adapter;
    ImageView imageHeader;

    public TuesdayFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        imageHeader = (ImageView) view.findViewById(R.id.htab_header);
//        imageHeader.setImageResource(R.drawable.tuesday);

        db = new DBHandler(view.getContext());
        classes = db.getClasses("Tuesday");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ViewScheduleRecyclerViewAdapter(view.getContext(),classes);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            db = new DBHandler(getContext());
            classes = db.getClasses("Tuesday");
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new ViewScheduleRecyclerViewAdapter(getContext(),classes);
            recyclerView.setAdapter(adapter);
        }


    }
}
