package com.example.retrofit;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofit.Adapter.CommentAdapter;
import com.example.retrofit.Database.CommentDatabase;
import com.example.retrofit.Model.Comment;
import com.example.retrofit.Model.Post;
import com.example.retrofit.Repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Comment> commentList;
    private CommentAdapter adapter;
    private CommentRepository commentRepository;
    private CommentDatabase commentDatabase;
    private int postId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        recyclerView = findViewById(R.id.recyclerView);
        commentList = new ArrayList<>();
        postId = Integer.parseInt(getIntent().getStringExtra("post_id"));

        commentDatabase = CommentDatabase.getInstance(CommentsActivity.this);
        commentRepository = new CommentRepository(getApplication());

        commentList = commentDatabase.commentDao().getAllCommentsByPost(postId);
        setAdapter();
        commentRepository.requestTestDataFromServer(this, postId);

    }

    private void setAdapter() {
        CommentAdapter adapter = new CommentAdapter(this, commentList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}