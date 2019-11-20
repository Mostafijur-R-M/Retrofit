package com.fendonus.retrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fendonus.retrofit.CourseDetailsActivity;
import com.fendonus.retrofit.R;
import com.fendonus.retrofit.model.AllChapter;
import com.fendonus.retrofit.model.Video;

import java.util.List;

public class AllChapterAdapter extends RecyclerView.Adapter<AllChapterAdapter.MyViewHolder> {
    Context context;
    List<Video> videoList;
    String YOUTUBE_URL = "https://youtu.be/";

    public AllChapterAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;

    }

    @NonNull
    @Override
    public AllChapterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chapter_video_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllChapterAdapter.MyViewHolder holder,final int position) {

        String thumbLink = videoList.get(position).getThumb();
        String title = videoList.get(position).getTitle();
        holder.titleTF = Typeface.createFromAsset(context.getAssets(), "font/SolaimanLipi.ttf");
        holder.titleTV.setTypeface(holder.titleTF);
        Glide.with(context).load(thumbLink).into(holder.thumbIV);
        holder.titleTV.setText(title);
        final Object notes = videoList.get(position).getNote();
        final Object files = videoList.get(position).getFile();



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String link = videoList.get(position).getYtd();
                String videoLink = YOUTUBE_URL+link;
                //Log.e("asd", ""+notes);
                /*Intent intent = new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("videoLink", link);
                context.startActivity(intent);*/
                ((CourseDetailsActivity)context).setNewVideoLink(link);
                ((CourseDetailsActivity)context).setNotes(""+notes);
                ((CourseDetailsActivity)context).setFiles(""+files);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Typeface titleTF;
        ImageView thumbIV;
        TextView titleTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbIV = itemView.findViewById(R.id.thumb_iv_id);
            titleTV = itemView.findViewById(R.id.video_title_itv_id);
        }
    }
}
