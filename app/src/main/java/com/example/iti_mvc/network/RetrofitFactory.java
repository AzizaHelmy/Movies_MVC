package com.example.iti_mvc.network;

import android.content.Context;

import com.example.iti_mvc.model.Movies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory implements Callback<List<Movies>> {
    static final String url = "https://api.androidhive.info/";
    static List<Movies> movies;
    private final Context context;
    NetworkDelegate networkDelegate;

    public RetrofitFactory(Context context, NetworkDelegate networkDelegate) {
        this.context = context;
        this.networkDelegate = networkDelegate;
        movies = new ArrayList<>();
    }

    public List<Movies> start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesInterface moviesInterface = retrofit.create(MoviesInterface.class);
        Call<List<Movies>> listCall = moviesInterface.getAllMovies();
        listCall.enqueue(this);
        return movies;
    }
//============================================================

    @Override
    public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
        if (response.isSuccessful()) {
            networkDelegate.onSuccessResult(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<Movies>> call, Throwable t) {
        networkDelegate.onFailureResult(t.getMessage());
    }
}
