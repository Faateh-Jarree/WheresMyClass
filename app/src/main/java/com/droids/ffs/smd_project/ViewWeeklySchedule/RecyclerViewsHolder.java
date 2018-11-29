package com.droids.ffs.smd_project.ViewWeeklySchedule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.droids.ffs.smd_project.R;

public class RecyclerViewsHolder extends RecyclerView.ViewHolder{


    public TextView Name;
    public TextView Section;
    public TextView Room;
    public TextView Time;

    public TextView getRemindertime() {
        return Remindertime;
    }

    public void setRemindertime(TextView remindertime) {
        Remindertime = remindertime;
    }

    public TextView Remindertime;

    public TextView getName() {
        return Name;
    }

    public void setName(TextView name) {
        Name = name;
    }

    public TextView getSection() {
        return Section;
    }

    public void setSection(TextView section) {
        Section = section;
    }

    public TextView getRoom() {
        return Room;
    }

    public void setRoom(TextView room) {
        Room = room;
    }

    public TextView getTime() {
        return Time;
    }

    public void setTime(TextView time) {
        Time = time;
    }

    public Class getClassItem() {
        return classItem;
    }

    public void setClassItem(Class classItem) {
        this.classItem = classItem;
    }

    private Class classItem;


    public void setItem(Class i) {
        classItem = i;
    }


    public RecyclerViewsHolder(final View itemView) {
        super(itemView);

        Name = (TextView) itemView.findViewById(R.id.name);
        Section = (TextView) itemView.findViewById(R.id.section);
        Room = (TextView) itemView.findViewById(R.id.room);
        Time = (TextView) itemView.findViewById(R.id.classtime);
        Remindertime = (TextView) itemView.findViewById(R.id.remindertime);
//        itemView.setOnClickListener(this);

//        itemView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(view.getContext(), MainActivityB.class);
//
//                intent.putExtra("Name",classItem.getCountryName());
////                intent.putExtra("Flag", countryItem.getCountryFlag());
//
//                image_view.buildDrawingCache();
//                Bitmap bitmap = image_view.getDrawingCache();
//                intent.putExtra("BitmapImage", bitmap);
//
//                view.getContext().startActivity(intent);
//
//
//            }


//        });


    }

}

