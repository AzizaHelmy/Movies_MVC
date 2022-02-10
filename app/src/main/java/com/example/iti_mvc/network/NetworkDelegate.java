package com.example.iti_mvc.network;

import com.example.iti_mvc.model.Movies;

import java.util.List;


public interface NetworkDelegate {
    public void onSuccessResult(List<Movies> movies);
    public void onFailureResult(String movies);
}
