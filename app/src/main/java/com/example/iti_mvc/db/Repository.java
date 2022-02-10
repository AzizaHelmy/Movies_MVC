package com.example.iti_mvc.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.iti_mvc.model.Movies;

import java.util.List;

public class Repository {
    private MovieDAO movieDAO;
    private Context context;
    private LiveData<List<Movies>> storedMovies;

    public Repository(Context context) {
        this.context = context;
        MovieDataBase dataBase = MovieDataBase.getInstance(context.getApplicationContext());
        movieDAO = dataBase.movieDAO();
         storedMovies=movieDAO.getAllFavMovies();
    }
    //======================================================
    public LiveData<List<Movies>> getStoredMovies() {
        return storedMovies;
    }
    //=============================================
    public void insert(Movies movie) {
        System.out.println("in insert");
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDAO.insertMovie(movie);
            }
        }).start();
    }

    //==========================================
    public void delete(Movies movie) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDAO.deleteMovie(movie);
            }
        }).start();
    }
    //====================================
    public int isFav(String title) {
        int res=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
             //res=movieDAO.isFavorite(title);
            }
        }).start();
        return res;
    }
}
