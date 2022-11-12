package com.example.retrofit.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.CommentsActivity;
import com.example.retrofit.Database.PostDatabase;
import com.example.retrofit.MainActivity;
import com.example.retrofit.Model.Post;
import com.example.retrofit.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Post> postList;
    private PostDatabase postDatabase;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView text_view_userId;
        TextView text_view_id;
        TextView text_view_title;
        TextView text_view_body;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            text_view_userId = itemView.findViewById(R.id.text_view_userId);
            text_view_id = itemView.findViewById(R.id.text_view_id);
            text_view_title = itemView.findViewById(R.id.text_view_title);
            text_view_body = itemView.findViewById(R.id.text_view_body);
        }
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Post post = postList.get(position);

        holder.text_view_userId.setText("userId: " + post.getUserId());
        holder.text_view_id.setText("id: " + post.getId());
        holder.text_view_title.setText("title: " + post.getTitle());
        holder.text_view_body.setText("body: " + post.getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentsActivity.class);
                intent.putExtra("post",postList.get(position));
                intent.putExtra("post_id",""+postList.get(position).getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

}
