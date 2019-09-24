package com.fendonus.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        String courseTitle = getIntent().getStringExtra("courseName");
        courseTV = findViewById(R.id.course_title_tv_id);
        courseTV.setText(courseTitle);
        recyclerView = findViewById(R.id.chapter_recyler_view_id);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        networkCall();
    }

    private void networkCall() {
        allChapterViewModel = ViewModelProviders.of(this).get(AllChapterViewModel.class);
        allChapterViewModel.listLiveData().observe(this, new Observer<List<AllChapter>>() {
            @Override
            public void onChanged(List<AllChapter> allChapters) {
                videoList = allChapters.get(0).getVideo();
                allChapterAdapter = new AllChapterAdapter(CourseDetailsActivity.this, videoList);
                recyclerView.setAdapter(allChapterAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }
}
