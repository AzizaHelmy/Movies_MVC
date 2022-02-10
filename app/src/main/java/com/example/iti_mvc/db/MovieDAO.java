package com.example.iti_mvc.db;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.iti_mvc.model.Movies;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(Movies movie);//addFavMovie

    @Delete
    void deleteMovie(Movies movie);//deletFav

    @Query("SELECT*From movies")
    LiveData<List<Movies>> getAllFavMovies();

    @Query("SELECT EXISTS (SELECT 1 FROM movies WHERE title=:title)")
    int isFavorite(String title);

    @Query("DELETE FROM movies WHERE title = :title")
    void deleteByUserId(String title);
}
