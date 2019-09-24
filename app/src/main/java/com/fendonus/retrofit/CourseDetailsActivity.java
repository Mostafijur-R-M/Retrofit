package com.fendonus.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fendonus.retrofit.adapter.AllChapterAdapter;
import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.Video;
import com.fendonus.retrofit.viewmodel.AllChapterViewModel;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {

    TextView courseTV;
    RecyclerView recyclerView;
    AllChapterViewModel allChapterViewModel;
    List<Video> videoList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    AllChapterAdapter allChapterAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);


       // Log.e("id",id);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String courseTitle = getIntent().getStringExtra("courseName");
        courseTV = findViewById(R.id.course_title_tv_id);
        courseTV.setText(courseTitle);
        recyclerView = findViewById(R.id.chapter_recyler_view_id);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        networkCall();
    }

    private void networkCall() {
        final String id = getIntent().getStringExtra("position");
        Log.e("position:", id);
        allChapterViewModel = ViewModelProviders.of(this).get(AllChapterViewModel.class);
        allChapterViewModel.listLiveData().observe(this, new Observer<List<AllChapter>>() {
            @Override
            public void onChanged(List<AllChapter> allChapters) {
                progressDialog.dismiss();
                videoList = allChapters.get(Integer.parseInt(id)).getVideo();
                allChapterAdapter = new AllChapterAdapter(CourseDetailsActivity.this, videoList);
                recyclerView.setAdapter(allChapterAdapter);
                recyclerView.setLayoutManager(layoutManager);
                Log.e("size: ", ""+videoList.size());
            }
        });
    }
}
