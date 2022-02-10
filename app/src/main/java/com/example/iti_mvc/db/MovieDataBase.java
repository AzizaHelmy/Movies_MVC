package com.example.iti_mvc.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.iti_mvc.model.Movies;

@Database(entities = {Movies.class}, version = 2)
public abstract class MovieDataBase extends RoomDatabase {
    private static MovieDataBase instance = null;
    public abstract MovieDAO movieDAO();
    public static synchronized MovieDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieDataBase.class, "movies")
                    .build();
        }
        return instance;
    }

}
