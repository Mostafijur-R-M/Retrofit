package com.fendonus.retrofit;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.fendonus.retrofit.adapter.AllCourseAdapter;
import com.fendonus.retrofit.model.AllCourse;
import com.fendonus.retrofit.model.Course;
import com.fendonus.retrofit.viewmodel.AllCourseViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    AllCourseViewModel allCourseViewModel;
    List<Course> courseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    AllCourseAdapter allCourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        recyclerView = findViewById(R.id.recylerViewId);
        layoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        networkCall();

    }

    private void networkCall() {
        allCourseViewModel = ViewModelProviders.of(this).get(AllCourseViewModel.class);
        allCourseViewModel.liveData().observe(this, new Observer<AllCourse>() {
            @Override
            public void onChanged(AllCourse allCourse) {
                progressDialog.dismiss();
                courseList = allCourse.getCourses();
                allCourseAdapter = new AllCourseAdapter(MainActivity.this, courseList);
                recyclerView.setAdapter(allCourseAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        });

    }
}
