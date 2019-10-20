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
import com.fendonus.retrofit.CourseDetailsActivity;
import com.fendonus.retrofit.R;
import com.fendonus.retrofit.model.Video;
import com.fendonus.retrofit.view.CyberSecurityActivity;

import java.util.List;

public class CyberSecurityAdapter extends RecyclerView.Adapter<CyberSecurityAdapter.MyViewHolder> {
    Context context;
    List<Video> videoList;
    String YOUTUBE_URL = "https://youtu.be/";

    public CyberSecurityAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }
    @NonNull
    @Override
    public CyberSecurityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chapter_video_row, parent, false);
        return new CyberSecurityAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CyberSecurityAdapter.MyViewHolder holder, final int position) {
        String thumbLink = videoList.get(position).getThumb();
        String title = videoList.get(position).getTitle();
        Glide.with(context).load(thumbLink).into(holder.thumbIV);
        holder.titleTV.setText(title);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String link = videoList.get(position).getYtd();
                String videoLink = YOUTUBE_URL+link;
                Log.e("link: ", videoLink);

                /*Intent intent = new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("videoLink", link);
                context.startActivity(intent);*/
                ((CyberSecurityActivity)context).setNewVideoLink(link);
            }
        });
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
