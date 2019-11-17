package com.fendonus.retrofit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fendonus.retrofit.R;
import com.fendonus.retrofit.model.AllChapter;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyCategoryViewHolder> {

    private Context context;
    List<AllChapter> allChapterList;

    public CategoryListAdapter(Context context, List<AllChapter> allChapterList) {
        this.context = context;
        this.allChapterList = allChapterList;
    }

    @NonNull
    @Override
    public MyCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_layout, parent, false);
        return new MyCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoryViewHolder holder, int position) {
        //holder.categoryName.setText(allChapterList.get(position).getTitle());

        holder.categoryName.setText(allChapterList.get(position).getTitle());
        //Log.e("asdf", allChapterList.get(1).getTitle());
    }

    @Override
    public int getItemCount() {
        return allChapterList.size();
    }

    public class MyCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        public MyCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.category_name_tv_id);
        }
    }
}
