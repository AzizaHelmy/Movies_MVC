package com.example.iti_mvc.favMovies.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iti_mvc.R;
import com.example.iti_mvc.db.Repository;
import com.example.iti_mvc.favMovies.view.FavMovieAdapter;
import com.example.iti_mvc.favMovies.view.OnFavClickListener;
import com.example.iti_mvc.model.Movies;

import java.util.ArrayList;
import java.util.List;

public class FavMovieActivity extends AppCompatActivity implements OnFavClickListener {
    RecyclerView recyclerFav;
    FavMovieAdapter favMovieAdapter;
    List<Movies> movies;
    Repository repository;
    ImageView imgHolderEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie);
        recyclerFav = findViewById(R.id.rv_fav_movie);
        imgHolderEmpty = findViewById(R.id.empty_iv);
        repository = new Repository(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFav.setLayoutManager(layoutManager);
        favMovieAdapter = new FavMovieAdapter(FavMovieActivity.this, new ArrayList<Movies>(), this);
        recyclerFav.setAdapter(favMovieAdapter);
        repository.getStoredMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                favMovieAdapter.setList(movies);
                favMovieAdapter.notifyDataSetChanged();
                checkForFavMovies();
            }
        });
    }

    @Override
    public void onFavClick(Movies movie) {
        //delet from repo
        repository.delete(movie);
        Toast.makeText(this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
        favMovieAdapter.notifyDataSetChanged();
        checkForFavMovies();
    }
//===============================================
    private void checkForFavMovies() {

        if (repository.getStoredMovies().getValue().isEmpty()) {
            recyclerFav.setVisibility(View.GONE);
            imgHolderEmpty.setVisibility(View.VISIBLE);
        } else {
            imgHolderEmpty.setVisibility(View.GONE);
            recyclerFav.setVisibility(View.VISIBLE);
        }
    }
    //=======================================

}