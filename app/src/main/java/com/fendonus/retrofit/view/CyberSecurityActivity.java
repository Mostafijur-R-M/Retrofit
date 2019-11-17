package com.fendonus.retrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.fendonus.retrofit.CourseDetailsActivity;
import com.fendonus.retrofit.MainActivity;
import com.fendonus.retrofit.R;
import com.fendonus.retrofit.adapter.AllChapterAdapter;
import com.fendonus.retrofit.adapter.CyberSecurityAdapter;
import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.AllCourse;
import com.fendonus.retrofit.model.Video;
import com.fendonus.retrofit.viewmodel.AllChapterViewModel;
import com.fendonus.retrofit.viewmodel.CyberSecurityViewModel;
import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.model.YTParams;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CyberSecurityActivity extends AppCompatActivity {

    TextView courseTV;
    RecyclerView recyclerView;
    CyberSecurityViewModel cyberSecurityViewModel;
    List<Video> videoList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    CyberSecurityAdapter cyberSecurityAdapter;
    YoutubePlayerView youtubePlayerView;
    CardView gkCV, googleAcountCV, facebookAccountCV, smartphonesAboutCV, mindQnaCV;
    RecyclerView gkRV, googleAcountRV, facebookAccountRV, smartphonesAboutRV, mindQnaRV;
    private CircleImageView backButtonIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_security);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        courseTV = findViewById(R.id.course_title_tv_id);
        String courseTitle = getIntent().getStringExtra("courseName");
        courseTV.setText(courseTitle);

        youtubePlayerView = findViewById(R.id.youtubePlayerView);

        gkRV = findViewById(R.id.gk_recyler_view_id);
        googleAcountRV = findViewById(R.id.google_account_recyler_view_id);
        facebookAccountRV = findViewById(R.id.facebook_account_recyler_view_id);
        smartphonesAboutRV = findViewById(R.id.smartphones_about_recyler_view_id);
        mindQnaRV = findViewById(R.id.mind_qna_recyler_view_id);

        gkCV = findViewById(R.id.gk_cv_id);
        googleAcountCV = findViewById(R.id.google_account_cv_id);
        facebookAccountCV = findViewById(R.id.facebook_account_cv_id);
        smartphonesAboutCV = findViewById(R.id.smartphones_about_cv_id);
        mindQnaCV = findViewById(R.id.mind_qna_cv_id);

        backButtonIV = findViewById(R.id.course_back_iv_id);

        backButtonIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CyberSecurityActivity.this, MainActivity.class));
                finish();
            }
        });
        gkCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gkRV.setVisibility(View.VISIBLE);
                googleAcountRV.setVisibility(View.GONE);
                facebookAccountRV.setVisibility(View.GONE);
                smartphonesAboutRV.setVisibility(View.GONE);
                mindQnaRV.setVisibility(View.GONE);
                networkCall(0);
                gkRV.setAdapter(cyberSecurityAdapter);
                gkRV.setLayoutManager(layoutManager);
            }
        });
        googleAcountCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gkRV.setVisibility(View.GONE);
                googleAcountRV.setVisibility(View.VISIBLE);
                facebookAccountRV.setVisibility(View.GONE);
                smartphonesAboutRV.setVisibility(View.GONE);
                mindQnaRV.setVisibility(View.GONE);
                networkCall(1);
                googleAcountRV.setAdapter(cyberSecurityAdapter);
                googleAcountRV.setLayoutManager(layoutManager);
            }
        });
        facebookAccountCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gkRV.setVisibility(View.GONE);
                googleAcountRV.setVisibility(View.GONE);
                facebookAccountRV.setVisibility(View.VISIBLE);
                smartphonesAboutRV.setVisibility(View.GONE);
                mindQnaRV.setVisibility(View.GONE);
                networkCall(2);
                facebookAccountRV.setAdapter(cyberSecurityAdapter);
                facebookAccountRV.setLayoutManager(layoutManager);
            }
        });
        smartphonesAboutCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gkRV.setVisibility(View.GONE);
                googleAcountRV.setVisibility(View.GONE);
                facebookAccountRV.setVisibility(View.GONE);
                smartphonesAboutRV.setVisibility(View.VISIBLE);
                mindQnaRV.setVisibility(View.GONE);
                networkCall(3);
                smartphonesAboutRV.setAdapter(cyberSecurityAdapter);
                smartphonesAboutRV.setLayoutManager(layoutManager);
            }
        });
        mindQnaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gkRV.setVisibility(View.GONE);
                googleAcountRV.setVisibility(View.GONE);
                facebookAccountRV.setVisibility(View.GONE);
                smartphonesAboutRV.setVisibility(View.GONE);
                mindQnaRV.setVisibility(View.VISIBLE);
                networkCall(4);
                mindQnaRV.setAdapter(cyberSecurityAdapter);
                mindQnaRV.setLayoutManager(layoutManager);
            }
        });
    }
    private void networkCall(final int position) {
        //final String id = getIntent().getStringExtra("position");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cyberSecurityViewModel = ViewModelProviders.of(this).get(CyberSecurityViewModel.class);
        cyberSecurityViewModel.listLiveData().observe(this, new Observer<List<AllChapter>>() {
            @Override
            public void onChanged(List<AllChapter> allChapters) {
                //progressDialog.dismiss();
                videoList = allChapters.get(position).getVideo();
                cyberSecurityAdapter = new CyberSecurityAdapter(CyberSecurityActivity.this, videoList);
            }
        });
    }
    public void setNewVideoLink(String link){
        youtubePlayVideo(link);
    }
    private void youtubePlayVideo(final String videoId) {

        YTParams params = new YTParams();
        youtubePlayerView.setAutoPlayerHeight(this);
        // initialize YoutubePlayerCallBackListener and VideoID
        youtubePlayerView.initialize(videoId, new YoutubePlayerView.YouTubeListener() {
            @Override
            public void onReady() {
                youtubePlayerView.play();
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
        //youtubePlayerView.pause();
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
}
