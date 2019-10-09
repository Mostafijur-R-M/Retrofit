package com.fendonus.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
    /*ViewPager viewPager;
    TabAccessorAdapter tabAccessorAdapter;*/

    CardView accountCV, phoneCV, computerCV, qnaCV;
    RecyclerView accountRV, phoneRV, computerRV, qnaRV;

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

        /*progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/


        youtubePlayerView = findViewById(R.id.youtubePlayerView);

        accountRV = findViewById(R.id.chapter_recyler_view_id);
        phoneRV = findViewById(R.id.phone_recyler_view_id);
        computerRV = findViewById(R.id.computer_recyler_view_id);
        qnaRV = findViewById(R.id.qna_recyler_view_id);

        accountCV = findViewById(R.id.account_cv_id);
        phoneCV = findViewById(R.id.phone_cv_id);
        computerCV = findViewById(R.id.computer_cv_id);
        qnaCV = findViewById(R.id.qna_cv_id);

        accountCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccountData();
            }
        });
        phoneCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountRV.setVisibility(View.GONE);
                phoneRV.setVisibility(View.VISIBLE);
                computerRV.setVisibility(View.GONE);
                qnaRV.setVisibility(View.GONE);
                networkCall(1);
                phoneRV.setAdapter(allChapterAdapter);
                phoneRV.setLayoutManager(layoutManager);
            }
        });
        computerCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountRV.setVisibility(View.GONE);
                phoneRV.setVisibility(View.GONE);
                computerRV.setVisibility(View.VISIBLE);
                qnaRV.setVisibility(View.GONE);
                networkCall(2);
                computerRV.setAdapter(allChapterAdapter);
                computerRV.setLayoutManager(layoutManager);
            }
        });
        qnaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountRV.setVisibility(View.GONE);
                phoneRV.setVisibility(View.GONE);
                computerRV.setVisibility(View.GONE);
                qnaRV.setVisibility(View.VISIBLE);
                networkCall(3);
                qnaRV.setAdapter(allChapterAdapter);
                qnaRV.setLayoutManager(layoutManager);
            }
        });
        /*tabLayout = findViewById(R.id.mains_tab_id);
        viewPager = findViewById(R.id.main_tabs_view_pager_id);
        tabAccessorAdapter = new TabAccessorAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAccessorAdapter);
        tabLayout.setupWithViewPager(viewPager);*/

        String courseTitle = getIntent().getStringExtra("courseName");
        courseTV = findViewById(R.id.course_title_tv_id);
        //youTubePlayerView = findViewById(R.id.youtube_player_view_id);
        //playBTN = findViewById(R.id.play_button_id);

        courseTV.setText(courseTitle);
        recyclerView = findViewById(R.id.chapter_recyler_view_id);
        //layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        String link = getIntent().getStringExtra("videoLink");
        yputubePlayVideo("o5NQ7a-oa8s");
        yputubePlayVideo(link);
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
        getAccountData();
    }

    private void getAccountData() {
        accountRV.setVisibility(View.VISIBLE);
        phoneRV.setVisibility(View.GONE);
        computerRV.setVisibility(View.GONE);
        qnaRV.setVisibility(View.GONE);
        networkCall(0);
        recyclerView.setAdapter(allChapterAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void yputubePlayVideo(final String videoId) {
        YTParams params = new YTParams();
        youtubePlayerView.setAutoPlayerHeight(this);
        // initialize YoutubePlayerCallBackListener and VideoID
        youtubePlayerView.initialize(videoId, new YoutubePlayerView.YouTubeListener() {
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
    private void networkCall(final int position) {
        //final String id = getIntent().getStringExtra("position");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allChapterViewModel = ViewModelProviders.of(this).get(AllChapterViewModel.class);
        allChapterViewModel.listLiveData().observe(this, new Observer<List<AllChapter>>() {
            @Override
            public void onChanged(List<AllChapter> allChapters) {
                //progressDialog.dismiss();
                videoList = allChapters.get(position).getVideo();
                allChapterAdapter = new AllChapterAdapter(CourseDetailsActivity.this, videoList);
            }
        });
    }
}
