package com.fendonus.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fendonus.retrofit.adapter.AllChapterAdapter;
import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.Video;
import com.fendonus.retrofit.model.YouTubeConfig;
import com.fendonus.retrofit.viewmodel.AllChapterViewModel;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailsActivity extends YouTubeBaseActivity{

    TextView courseTV;
    RecyclerView recyclerView;
    AllChapterViewModel allChapterViewModel;
    List<Video> videoList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    AllChapterAdapter allChapterAdapter;
    ProgressDialog progressDialog;
    private static final String TAG = "CourseDetailsActivity";
    YouTubePlayerView youTubePlayerView;
    Button playBTN;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private AppCompatActivity appCompatActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCompatActivity = new AppCompatActivity();
        setContentView(R.layout.activity_course_details);

        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }*/

       // Log.e("id",id);
        /*progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/

        String courseTitle = getIntent().getStringExtra("courseName");
        courseTV = findViewById(R.id.course_title_tv_id);
        youTubePlayerView = findViewById(R.id.youtube_player_view_id);
        playBTN = findViewById(R.id.play_button_id);


        courseTV.setText(courseTitle);
        recyclerView = findViewById(R.id.chapter_recyler_view_id);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //networkCall();
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                /*List<String> videoList = new ArrayList<>();
                videoList.add("W4hTJybfU7s");
                videoList.add("W4hTJybfU7s");*/
                youTubePlayer.loadVideo("_Q48KElcVVA");
                //youTubePlayer.loadPlaylist("zZloGrbD9kQ");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);
            }
        });
    }

   /* private void networkCall() {
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
    }*/
}
