package com.example.retrofit.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofit.Model.Comment;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Comment> commentList);

    @Query("SELECT * FROM comment")
    List<Comment> getAllComments();

    @Query("SELECT * FROM comment WHERE postId = :postId")
    List<Comment> getAllCommentsByPost(int postId);

    @Query("DELETE FROM comment")
    void deleteAll();
}
