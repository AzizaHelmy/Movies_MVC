package com.example.iti_mvc.network;

import com.example.iti_mvc.model.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesInterface {
    @GET("json/movies.json")
    Call<List<Movies>> getAllMovies();
}
