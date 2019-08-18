package com.fendonus.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fendonus.retrofit.R;
import com.fendonus.retrofit.model.RetroPhoto;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    private List<RetroPhoto> dataList;
    private Context context;

    public CustomAdapter(List<RetroPhoto> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.textTitle.setText(dataList.get(position).getTitle());
        Picasso.Builder builder = new Picasso.Builder(context);

        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private ImageView coverImage;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTilteId);
            coverImage = itemView.findViewById(R.id.coverImageId);
        }
    }

    /*@NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        return new CustomViewGolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewGolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }*/

    /*public class CustomViewHolder extends RecyclerView.ViewHolder {




        public CustomViewHolderolder(@NonNull View itemView, View view) {
            super(itemView);

            this.view = view;

            textTitle = itemView.findViewById(R.id.textTilteId);
            coverImage = itemView.findViewById(R.id.coverImageId);
        }
    }*/
}
