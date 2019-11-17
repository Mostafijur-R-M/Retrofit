package com.fendonus.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fendonus.retrofit.adapter.AllChapterAdapter;
import com.fendonus.retrofit.adapter.CategoryListAdapter;
import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.Video;
import com.fendonus.retrofit.viewmodel.AllChapterViewModel;
import com.google.android.youtube.player.YouTubePlayer;
import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.model.YTParams;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CourseDetailsActivity extends AppCompatActivity {

    TextView courseTV;
    RecyclerView recyclerView, categoryRV;
    AllChapterViewModel allChapterViewModel;
    List<Video> videoList = new ArrayList<>();
    List<AllChapter> chapterList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    AllChapterAdapter allChapterAdapter;
    RecyclerView.LayoutManager categoryLayoutManager;
    CategoryListAdapter categoryListAdapter;
    ProgressDialog progressDialog;
    private static final String TAG = "CourseDetailsActivity";
    //YouTubePlayerView youTubePlayerView;
    Button playBTN;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private AppCompatActivity appCompatActivity;
    YoutubePlayerView youtubePlayerView;
    CardView cat1CV, cat2CV, cat3CV, cat4CV, cat5CV;
    RecyclerView accountRV, phoneRV, computerRV, qnaRV;
    private CircleImageView backButtonIV;
    private TextView cat1, cat2, cat3, cat4, cat5;

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

        cat1CV = findViewById(R.id.account_cv_id);
        cat2CV = findViewById(R.id.phone_cv_id);
        cat3CV = findViewById(R.id.computer_cv_id);
        cat4CV = findViewById(R.id.qna_cv_id);
        cat5CV = findViewById(R.id.cat5_cv_id);

        cat1 = findViewById(R.id.cat1_tv_id);
        cat2 = findViewById(R.id.cat2_tv_id);
        cat3 = findViewById(R.id.cat3_tv_id);
        cat4 = findViewById(R.id.cat4_tv_id);
        cat5 = findViewById(R.id.cat5_tv_id);

        backButtonIV = findViewById(R.id.course_back_iv_id);

        backButtonIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseDetailsActivity.this, MainActivity.class));
                finish();
            }
        });

        cat1CV.setClickable(true);
        networkCall(0);
        cat1CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountRV.setVisibility(View.VISIBLE);
                phoneRV.setVisibility(View.GONE);
                computerRV.setVisibility(View.GONE);
                qnaRV.setVisibility(View.GONE);
                networkCall(0);
                accountRV.setAdapter(allChapterAdapter);
                accountRV.setLayoutManager(layoutManager);
                //getAccountData();
            }
        });
        cat2CV.setOnClickListener(new View.OnClickListener() {
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
        cat3CV.setOnClickListener(new View.OnClickListener() {
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
        cat4CV.setOnClickListener(new View.OnClickListener() {
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

        String courseTitle = getIntent().getStringExtra("courseName");



        courseTV = findViewById(R.id.course_title_tv_id);
        //youTubePlayerView = findViewById(R.id.youtube_player_view_id);
        //playBTN = findViewById(R.id.play_button_id);

        courseTV.setText(courseTitle);
        recyclerView = findViewById(R.id.chapter_recyler_view_id);
        //categoryRV = findViewById(R.id.category_rv_id);

        getCategoryTitle();
       // categoryShow();

        //String link = getIntent().getStringExtra("videoLink");
       /* youtubePlayVideo("o5NQ7a-oa8s");
        youtubePlayVideo(link);*/

       //setNewVideoLink(link);
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

    private void getCategoryTitle() {
        String id = getIntent().getStringExtra("course_id");

        categoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allChapterViewModel = ViewModelProviders.of(this).get(AllChapterViewModel.class);
        allChapterViewModel.listLiveData(id).observe(this, new Observer<List<AllChapter>>() {
            @Override
            public void onChanged(List<AllChapter> allChapters) {
                final int n = allChapters.size();
                Log.e("asdf", String.valueOf(n));
                
                for (int i = 0; i < allChapters.size(); i++){
                    //chapterList = allChapters.get(i).getTitle();
                    String title = allChapters.get(i).getTitle();
                    Log.e("asdfg", title);
                    if (n==5){
                        cat1.setVisibility(View.VISIBLE);
                        cat2.setVisibility(View.VISIBLE);
                        cat3.setVisibility(View.VISIBLE);
                        cat4.setVisibility(View.VISIBLE);
                        cat5.setVisibility(View.VISIBLE);
                        cat1.setText(allChapters.get(0).getTitle());
                        cat2.setText(allChapters.get(1).getTitle());
                        cat3.setText(allChapters.get(2).getTitle());
                        cat4.setText(allChapters.get(3).getTitle());
                        cat5.setText(allChapters.get(4).getTitle());
                    }else if (n==4){
                        cat1.setVisibility(View.VISIBLE);
                        cat2.setVisibility(View.VISIBLE);
                        cat3.setVisibility(View.VISIBLE);
                        cat4.setVisibility(View.VISIBLE);
                        cat1.setText(allChapters.get(0).getTitle());
                        cat2.setText(allChapters.get(1).getTitle());
                        cat3.setText(allChapters.get(2).getTitle());
                        cat4.setText(allChapters.get(3).getTitle());
                    }else if (n==3){
                        cat1.setVisibility(View.VISIBLE);
                        cat2.setVisibility(View.VISIBLE);
                        cat3.setVisibility(View.VISIBLE);
                        cat1.setText(allChapters.get(0).getTitle());
                        cat2.setText(allChapters.get(1).getTitle());
                        cat3.setText(allChapters.get(2).getTitle());
                    }else if (n==2){
                        cat1.setVisibility(View.VISIBLE);
                        cat2.setVisibility(View.VISIBLE);
                        cat1.setText(allChapters.get(0).getTitle());
                        cat2.setText(allChapters.get(1).getTitle());
                    }else if (n==1){
                        cat1.setVisibility(View.VISIBLE);
                        cat1.setText(allChapters.get(0).getTitle());
                    }else if (n==0){
                        Toast.makeText(CourseDetailsActivity.this, "There is no Chapter", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(CourseDetailsActivity.this, "There is no video", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CourseDetailsActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }
        });
       /* //categoryRV.setLayoutManager(new LinearLayoutManager(this));
        categoryListAdapter = new CategoryListAdapter(this, allChapterList);
        //categoryListAdapter.setClickListener(this);
        categoryRV.setAdapter(categoryListAdapter);
        categoryRV.setLayoutManager(categoryLayoutManager);*/
    }

    public void setNewVideoLink(String link){
        youtubePlayVideo(link);
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

    private void networkCall(final int position) {
        String id = getIntent().getStringExtra("course_id");
        //final String id = getIntent().getStringExtra("position");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allChapterViewModel = ViewModelProviders.of(this).get(AllChapterViewModel.class);
        allChapterViewModel.listLiveData(id).observe(this, new Observer<List<AllChapter>>() {
            @Override
            public void onChanged(List<AllChapter> allChapters) {
                //progressDialog.dismiss();
                videoList = allChapters.get(position).getVideo();
                allChapterAdapter = new AllChapterAdapter(CourseDetailsActivity.this, videoList);
            }
        });
    }
}
