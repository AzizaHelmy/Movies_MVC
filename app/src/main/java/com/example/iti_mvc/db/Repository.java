package com.example.iti_mvc.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.iti_mvc.model.Movies;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private MovieDAO movieDAO;
    private Context context;
    private LiveData<List<Movies>> storedMovies;

    public Repository(Context context) {
        this.context = context;
        MovieDataBase dataBase = MovieDataBase.getInstance(context.getApplicationContext());
        movieDAO = dataBase.movieDAO();
        storedMovies = movieDAO.getAllFavMovies();
    }
    //======================================================
    public LiveData<List<Movies>> getStoredMovies() {
        return storedMovies;
    }
    //======================================================
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
    public boolean getFav(String title) {
       Fav fav=new Fav();
       fav.setTitle(title);
        Thread th=new Thread(fav);
       th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  fav.isFlag();
    }

    public class Fav implements Runnable {
        private boolean flag=false;
        private String title;

        @Override
        public void run() {
            flag = movieDAO.isFavorite(title);
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public boolean isFlag() {
            return flag;
        }
    }

}
