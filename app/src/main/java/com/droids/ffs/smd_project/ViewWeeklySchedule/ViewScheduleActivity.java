package com.droids.ffs.smd_project.ViewWeeklySchedule;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.DBHandler;


public class ViewScheduleActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapse_toolbar;
    private ViewPager viewPager;
    private Toolbar toolbar;
    DBHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_schedule);

        //View weekly Schedule
        ViewSchdedule();
    }

    public void ViewSchdedule(){
        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        collapse_toolbar = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Weekly Schedule");

        //DYNAMIC TAB COLOR WITH PALETTE API, The color you see the Toolbar + TabLayout take, is picked from the header image.
        DynamicTabColor();

        //attach the ViewPager to our TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                //Set tab names like Monday,tuesday...
                switch (tab.getPosition()){
                    case 0:
                        tab.setText("Monday");
//                        Toast.makeText(MainActivity.this,"Tuesday",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        tab.setText("Tuesday");
//                        Toast.makeText(MainActivity.this,"Tuesday",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        tab.setText("Wednesday");
//                        Toast.makeText(MainActivity.this,"Wednesday",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        tab.setText("Thursday");
//                        Toast.makeText(MainActivity.this,"Wednesday",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        tab.setText("Friday");
//                        Toast.makeText(MainActivity.this,"Wednesday",Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ViewSchedulePagerAdapter adapter = new ViewSchedulePagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

    }

    public void DynamicTabColor(){

        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.monday);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {

                    int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                    int vibrantDarkColor = palette.getDarkVibrantColor(R.color.bgRowBackground);
                    collapse_toolbar.setContentScrimColor(vibrantColor);
                    collapse_toolbar.setStatusBarScrimColor(vibrantDarkColor);
                }
            });

        } catch (Exception e) {
            // if Bitmap fetch fails, fallback to primary colors
            Log.e("TAG", "onCreate: failed to create bitmap from background", e.fillInStackTrace());
            collapse_toolbar.setContentScrimColor(
                    ContextCompat.getColor(this, R.color.colorPrimary)
            );
            collapse_toolbar.setStatusBarScrimColor(
                    ContextCompat.getColor(this, R.color.colorPrimary)
            );
        }
    }
}
