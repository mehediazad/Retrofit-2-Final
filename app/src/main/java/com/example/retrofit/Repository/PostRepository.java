package com.example.retrofit.Repository;

import android.app.Application;
import android.content.Context;
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

public class PostRepository {
    private static PostDatabase postDatabase;
    private List<Post> getAllPosts;
    private List<Post> postList;
    private Api apiClient;
    private Context application;
    private PostDao postDao;


    public PostRepository(Context application) {
        this.application = application;
        apiClient = RetrofitClient.getRetrofit().create(Api.class);
        postDatabase = PostDatabase.getInstance(application);
        postDao = postDatabase.postDao();
        getAllPosts = postDao.getAllPosts();
    }
    public List<Post> getAllPosts() {
        return getAllPosts;
    }

    public void requestTestDataFromServer(Context context) {
        Call<List<Post>> call = apiClient.getAllPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Size: " + response.body().size(), Toast.LENGTH_SHORT).show();
                    postDatabase.postDao().insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(context, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

