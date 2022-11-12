package com.example.retrofit.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.retrofit.Model.Post;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {
    //Create database instance

    private static volatile PostDatabase INSTANCE;
    private static String DATABASE_NAME = "PostDatabase";

    public abstract PostDao postDao();

    public synchronized static PostDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Initialize Database
            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , PostDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
