package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.retrofit.Adapter.CommentAdapter;
import com.example.retrofit.Adapter.PostAdapter;
import com.example.retrofit.Database.CommentDatabase;
import com.example.retrofit.Database.PostDatabase;
import com.example.retrofit.Model.Comment;
import com.example.retrofit.Model.Post;
import com.example.retrofit.Repository.CommentRepository;
import com.example.retrofit.Repository.PostRepository;
import com.example.retrofit.Repository.createPostRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private List<Post> postList;
    private PostAdapter adapter;
    private PostRepository postRepository;
    private PostDatabase postDatabase;
    private ImageView btnCreateNewPost;
    private createPostRepository createPostRepository;
    private EditText editText_Title;
    private EditText editText_Text;
    private Button button_submit;
    //  private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        postList = new ArrayList<>();
        postDatabase = PostDatabase.getInstance(MainActivity.this);
        postRepository = new PostRepository(this);
        postList = postDatabase.postDao().getAllPosts();
        setAdapter();
        postRepository.requestTestDataFromServer(this);
        createPostRepository = new createPostRepository(this);
        btnCreateNewPost = findViewById(R.id.image);

        btnCreateNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create Dialog
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.child_add);

                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int hight = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, hight);
                dialog.show();

                // Initialize and assain Veriable
                EditText editText_Title = dialog.findViewById(R.id.editText_Title);
                EditText editText_Text = dialog.findViewById(R.id.editText_Text);
                Button button_submit = dialog.findViewById(R.id.button_submit);

                button_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // dismiss dialog
                        dialog.dismiss();

                        String title = editText_Title.getText().toString().trim();
                        String text = editText_Text.getText().toString().trim();

                        Post post = new Post(0,title,text);

                        createPostRepository.createPost(post);
                        postList = postDatabase.postDao().getAllPosts();
                        adapter.setPostList(postList);
                        postDatabase.postDao().insert(postList);
                        createPostRepository.requestTestDataFromServer(MainActivity.this, post);

                    }
                });
            }
        });

    }

    private void setAdapter() {
        adapter = new PostAdapter(this, postList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}