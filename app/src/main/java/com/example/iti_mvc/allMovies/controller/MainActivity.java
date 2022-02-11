package com.example.iti_mvc.allMovies.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iti_mvc.R;
import com.example.iti_mvc.allMovies.view.MovieAdapter;
import com.example.iti_mvc.allMovies.view.OnClickListener;
import com.example.iti_mvc.db.Repository;
import com.example.iti_mvc.model.Movies;
import com.example.iti_mvc.network.NetworkDelegate;
import com.example.iti_mvc.network.RetrofitFactory;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkDelegate, OnClickListener {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<Movies> movies;
    RetrofitFactory retrofitFactory;
    Repository repository;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.cons);
        retrofitFactory = new RetrofitFactory(this, this);
        repository = new Repository(this);
        movies = retrofitFactory.start();
        recyclerView = findViewById(R.id.rv_movie);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(MainActivity.this, movies, this, repository);
    }
    @Override
    public void onSuccessResult(List<Movies> movies) {
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.setList(movies);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureResult(String movies) {
        recyclerView.setVisibility(View.GONE);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onFavClicked(Movies movie, ImageView favImg) {
        if (favImg.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.ic_baseline_favorite_24).getConstantState()) {
            Toast.makeText(this, "removed", Toast.LENGTH_LONG).show();
            repository.delete(movie);
        } else {
            Snackbar.make(constraintLayout, "added to Fav", Snackbar.LENGTH_LONG).show();
            repository.insert(movie);
        }
        movieAdapter.notifyDataSetChanged();
    }
    @Override
    public void onShareClicked(Movies movie) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hey this film is so amazing " + movie.getTitle());
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share With "));
        }
    }
}