package com.yunwltn98.postingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yunwltn98.postingapp.R;
import com.yunwltn98.postingapp.model.Posting;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    Context context;
    ArrayList<Posting> PostList;

    public PostAdapter(Context context, ArrayList<Posting> postList) {
        this.context = context;
        PostList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Posting posting = PostList.get(position);
        holder.txtTitle.setText(posting.title);
        holder.txtbody.setText(posting.body);
    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtbody;
        ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtbody = itemView.findViewById(R.id.txtbody);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
