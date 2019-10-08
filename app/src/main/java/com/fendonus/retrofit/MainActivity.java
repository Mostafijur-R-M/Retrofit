package com.fendonus.retrofit;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.fendonus.retrofit.adapter.AllCourseAdapter;
import com.fendonus.retrofit.model.AllCourse;
import com.fendonus.retrofit.model.Course;
import com.fendonus.retrofit.viewmodel.AllCourseViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView, recyclerViewShows;
    ProgressDialog progressDialog;
    AllCourseViewModel allCourseViewModel;
    List<Course> courseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManager2;
    AllCourseAdapter allCourseAdapter;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setIcon(R.drawable.ic_loading);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);


        recyclerView = findViewById(R.id.recylerViewId);
        recyclerViewShows = findViewById(R.id.recylerViewShowsId);
        layoutManager = new GridLayoutManager(MainActivity.this,3);
        layoutManager2 = new GridLayoutManager(MainActivity.this,3);
        networkCall();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.draw_profile:
                        break;
                    case R.id.draw_logOut:
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void networkCall() {
        allCourseViewModel = ViewModelProviders.of(this).get(AllCourseViewModel.class);
        allCourseViewModel.liveData().observe(this, new Observer<List<AllCourse>>() {
            @Override
            public void onChanged(List<AllCourse> allCourses) {
                progressDialog.dismiss();
                courseList = allCourses.get(0).getCourses();

                allCourseAdapter = new AllCourseAdapter(MainActivity.this, courseList);
                recyclerView.setAdapter(allCourseAdapter);
                recyclerView.setLayoutManager(layoutManager2);

               /* if (courseList.get(0).getId() == 91001){
                    courseList = allCourses.get(0).getCourses();
                    allCourseAdapter = new AllCourseAdapter(MainActivity.this, courseList);
                    recyclerViewShows.setAdapter(allCourseAdapter);
                    recyclerViewShows.setLayoutManager(layoutManager);
                }*/
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_logo_foreground);
        builder.setMessage("Are you sure you want to leave?")
                .setCancelable(false)
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent setIntent = new Intent(Intent.ACTION_MAIN);
                        setIntent.addCategory(Intent.CATEGORY_HOME);
                        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(setIntent);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void courseDataProccess() {
        int n = courseList.size();
        for (int i = 0; i < n; i++){
            if (courseList.get(i).getId() < 91000){
               //TODO populate new arraylist without those five items.

            }
            else {
                //TODO populate new arraylist with those five items.
            }

        }
    }
}
