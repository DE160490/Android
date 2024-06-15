package com.example.exercisesixteen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> postList;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onEditClick(int post);
        void onDeleteClick(int post);
    }
    public PostAdapter(Context context, List<Post> postList, OnItemClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view, listener);
    }
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        FloatingActionButton btnDelete, btnUpdate;
        public PostViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            btnDelete = itemView.findViewById(R.id.delete);
            btnUpdate = itemView.findViewById(R.id.update);

            btnUpdate.setOnClickListener(v -> listener.onEditClick(getAdapterPosition()));
            btnDelete.setOnClickListener(v -> listener.onDeleteClick(getAdapterPosition()));
        }
    }
    public Post getPostAt(int position) {
        return postList.get(position);
    }
    public void removePost(int position) {
        if (position >= 0 && position < postList.size()) {
            postList.remove(position);
            notifyItemRemoved(position);
        }
    }
}

