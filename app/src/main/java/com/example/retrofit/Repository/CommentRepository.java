package com.example.retrofit.Repository;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.retrofit.Database.CommentDatabase;
import com.example.retrofit.Model.Comment;
import com.example.retrofit.Network.Api;
import com.example.retrofit.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {
    private static CommentDatabase commentDatabase;
    private List<Comment> getAllComments;
    private Api apiClient;
    private Application application;

    public CommentRepository(Application application) {
        this.application = application;

        apiClient = RetrofitClient.getRetrofit().create(Api.class);
        commentDatabase = CommentDatabase.getInstance(application);
        getAllComments = commentDatabase.commentDao().getAllComments();
    }

    public List<Comment> getAllComments() {
        return getAllComments();
    }

    public void requestTestDataFromServer(Context context, int id){

        Call<List<Comment>> call = apiClient.getAllComments(id);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                Toast.makeText(context, "Size: " + response.body().size(), Toast.LENGTH_SHORT).show();
                commentDatabase.commentDao().insert(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(context, "something went wrong...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
