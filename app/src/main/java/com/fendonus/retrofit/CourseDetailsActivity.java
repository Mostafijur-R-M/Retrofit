package com.fendonus.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fendonus.retrofit.adapter.AllChapterAdapter;
import com.fendonus.retrofit.adapter.TabAccessorAdapter;
import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.Video;
import com.fendonus.retrofit.model.YouTubeConfig;
import com.fendonus.retrofit.viewmodel.AllChapterViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.model.YTParams;

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
    private static final String TAG = "CourseDetailsActivity";
    YouTubePlayerView youTubePlayerView;
    Button playBTN;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private AppCompatActivity appCompatActivity;
    YoutubePlayerView youtubePlayerView;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabAccessorAdapter tabAccessorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCompatActivity = new AppCompatActivity();
        setContentView(R.layout.activity_course_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        youtubePlayerView = findViewById(R.id.youtubePlayerView);
        tabLayout = findViewById(R.id.mains_tab_id);
        viewPager = findViewById(R.id.main_tabs_view_pager_id);
        tabAccessorAdapter = new TabAccessorAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAccessorAdapter);

        tabLayout.setupWithViewPager(viewPager);
        String courseTitle = getIntent().getStringExtra("courseName");
        courseTV = findViewById(R.id.course_title_tv_id);
        //youTubePlayerView = findViewById(R.id.youtube_player_view_id);
        //playBTN = findViewById(R.id.play_button_id);

        courseTV.setText(courseTitle);
        recyclerView = findViewById(R.id.chapter_recyler_view_id);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        networkCall();
        yputubePlayVideo();
        /*onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                *//*List<String> videoList = new ArrayList<>();
                videoList.add("W4hTJybfU7s");
                videoList.add("W4hTJybfU7s");*//*
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
        });*/
    }

    private void yputubePlayVideo() {
        YTParams params = new YTParams();

        youtubePlayerView.setAutoPlayerHeight(this);
        // initialize YoutubePlayerCallBackListener and VideoID
        youtubePlayerView.initialize("_Q48KElcVVA", new YoutubePlayerView.YouTubeListener() {
            @Override
            public void onReady() {

            }

            @Override
            public void onStateChange(YoutubePlayerView.STATE state) {

            }

            @Override
            public void onPlaybackQualityChange(String arg) {

            }

            @Override
            public void onPlaybackRateChange(String arg) {

            }

            @Override
            public void onError(String arg) {

            }

            @Override
            public void onApiChange(String arg) {

            }

            @Override
            public void onCurrentSecond(double second) {

            }

            @Override
            public void onDuration(double duration) {

            }

            @Override
            public void logs(String log) {

            }
        });
        // psuse video
        youtubePlayerView.pause();
        // play video when it's ready
        youtubePlayerView.play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // pause video when on the background mode.
        youtubePlayerView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // this is optional but you need.
        youtubePlayerView.destroy();
    }
    private void networkCall() {
        final String id = getIntent().getStringExtra("position");
        allChapterViewModel = ViewModelProviders.of(this).get(AllChapterViewModel.class);
        allChapterViewModel.listLiveData().observe(this, new Observer<List<AllChapter>>() {
            @Override
            public void onChanged(List<AllChapter> allChapters) {
                progressDialog.dismiss();
                videoList = allChapters.get(Integer.parseInt(id)).getVideo();
                allChapterAdapter = new AllChapterAdapter(CourseDetailsActivity.this, videoList);
                recyclerView.setAdapter(allChapterAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }
}
