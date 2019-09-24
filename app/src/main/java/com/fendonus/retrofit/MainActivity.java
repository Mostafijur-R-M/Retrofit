package com.fendonus.retrofit;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AdapterView;

import com.fendonus.retrofit.adapter.AllCourseAdapter;
import com.fendonus.retrofit.model.AllCourse;
import com.fendonus.retrofit.model.Course;
import com.fendonus.retrofit.viewmodel.AllCourseViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView, recyclerViewShows;
    ProgressDialog progressDialog;
    AllCourseViewModel allCourseViewModel;
    List<Course> courseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManager2;
    AllCourseAdapter allCourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setIcon(R.drawable.ic_loading);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        recyclerView = findViewById(R.id.recylerViewId);
        recyclerViewShows = findViewById(R.id.recylerViewShowsId);
        layoutManager = new GridLayoutManager(MainActivity.this,3);
        layoutManager2 = new GridLayoutManager(MainActivity.this,3);
        networkCall();

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

                //showAllCourse(allCourses);

                /*if (courseList.get(0).getId() == 91001){
                    courseList = allCourses.get(0).getCourses();
                    allCourseAdapter = new AllCourseAdapter(MainActivity.this, courseList);
                    recyclerViewShows.setAdapter(allCourseAdapter);
                    recyclerViewShows.setLayoutManager(layoutManager);
                }*/
            }
        });

    }

    private void showAllCourse(List<AllCourse> allCourses) {
        int n = courseList.size();
        Log.e("id", ""+n);
        for (int i = 0; i < n; i++){
            int id = courseList.get(i).getId();
            if (90080<id){
                Log.e("id:", String.valueOf(courseList.get(i).getId()));
                int a = courseList.get(i).getId();
                courseList = allCourses.get(a).getCourses();
                allCourseAdapter = new AllCourseAdapter(MainActivity.this, courseList);
                recyclerViewShows.setAdapter(allCourseAdapter);
                recyclerViewShows.setLayoutManager(layoutManager);
            }

        }
    }
}
