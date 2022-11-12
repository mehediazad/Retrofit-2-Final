package com.example.retrofit.Network;


import com.example.retrofit.Model.Comment;
import com.example.retrofit.Model.Post;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @GET("posts")
    Call<List<Post>> getAllPost();

    @GET("/posts/{id}/comments")
    Call<List<Comment>> getAllComments(@Path("id") int postID);


    @POST("posts")
    Call<Post> createPost(@Body Post post);

//    @FormUrlEncoded
//    @POST("posts")
//    Call<Post> createPost(
//            @Field("userId") int userId,
//            @Field("title") String title,
//            @Field("body") String text
//    );
//
//    @FormUrlEncoded
//    @POST("posts")
//    Call<Post> createPost(@FieldMap Map<String, String> fields);
}
