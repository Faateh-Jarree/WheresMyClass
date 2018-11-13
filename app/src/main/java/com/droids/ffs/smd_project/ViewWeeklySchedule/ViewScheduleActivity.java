package com.droids.ffs.smd_project.ViewWeeklySchedule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.droids.ffs.smd_project.Add_Table;
import com.droids.ffs.smd_project.MainActivity;
import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.SelectCourse.SelectCourseActivity;


public class ViewScheduleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private CollapsingToolbarLayout collapse_toolbar;
    private ViewPager viewPager;
    private Toolbar toolbar;
    DBHandler db;
    private DrawerLayout mDrawerLayout; // The Nav-Drawer Layout


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Running Fullscreen Mode
        MainActivity.runFullScreenMode(this);
        setContentView(R.layout.navigation_drawer);

        //View weekly Schedule
        ViewSchdedule();
        setDrawerAndToolBar(this);


    }

    public void ViewSchdedule(){
        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        collapse_toolbar = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setSubtitle("Weekly Schedule");

        //DYNAMIC TAB COLOR WITH PALETTE API, The color you see the Toolbar + TabLayout take, is picked from the header image.
//        DynamicTabColor();

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

    // Nav-Drawer and ToolBar Creation
    @NonNull
    protected void setDrawerAndToolBar(Activity act){

        Toolbar toolbar = findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setTitle(null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) act);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // If Back Button Pressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    // If the any options from the drawer menu is selected
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.selectTimeTable) {
            Intent filepicker = new Intent(Intent.ACTION_GET_CONTENT);
            filepicker.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            startActivityForResult(filepicker, 1);

        } else if (id == R.id.selectCourses) {
            Intent i = new Intent(this,SelectCourseActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        else if (id == R.id.setReminder){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
