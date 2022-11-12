package com.example.retrofit.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofit.Database.PostDao;
import com.example.retrofit.Database.PostDatabase;
import com.example.retrofit.Model.Post;
import com.example.retrofit.Network.Api;
import com.example.retrofit.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createPostRepository {
    private static final String TAG = "createPostRepository";
    private static PostDatabase postDatabase;
    private List<Post> getAllPosts;
    private List<Post> posts;
    private Api apiClient;
    private Context application;
    private PostDao postDao;

    public createPostRepository(Context application) {
        this.application = application;
        apiClient = RetrofitClient.getRetrofit().create(Api.class);
        postDatabase = postDatabase.getInstance(application);
        postDao = postDatabase.postDao();
        getAllPosts = postDao.getAllPosts();
    }

    public void createPost(Post post) {
        postDao.create(post);
    }

    public void requestTestDataFromServer(Context context, Post post) {
       // Log.d(TAG, "requestTestDataFromServer: " + post);
        Call<Post> call = apiClient.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "post successful: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(context, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
