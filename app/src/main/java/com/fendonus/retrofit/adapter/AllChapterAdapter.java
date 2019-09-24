package com.fendonus.retrofit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fendonus.retrofit.R;
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
    public void onBindViewHolder(@NonNull AllChapterAdapter.MyViewHolder holder, int position) {

        String thumbLink = videoList.get(position).getThumb();
        String title = videoList.get(position).getTitle();
        Glide.with(context).load(thumbLink).into(holder.thumbIV);
        holder.titleTV.setText(title);

        String link = videoList.get(position).getYtd();
        String videoLink = YOUTUBE_URL+link;
        Log.e("link: ", videoLink);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbIV;
        TextView titleTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbIV = itemView.findViewById(R.id.thumb_iv_id);
            titleTV = itemView.findViewById(R.id.video_title_itv_id);
        }
    }
}
