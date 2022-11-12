package com.example.retrofit.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofit.Model.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Post post);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Post> postList);

    @Query("SELECT * FROM post")
    List<Post> getAllPosts();

    @Query("DELETE FROM post")
    void deleteAll();

}
