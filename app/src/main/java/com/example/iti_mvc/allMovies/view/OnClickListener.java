package com.example.iti_mvc.allMovies.view;

import android.widget.ImageView;

import com.example.iti_mvc.model.Movies;

public interface OnClickListener {
    void onFavClicked(Movies movie, ImageView favImg);
    void onShareClicked(Movies movie);
}
