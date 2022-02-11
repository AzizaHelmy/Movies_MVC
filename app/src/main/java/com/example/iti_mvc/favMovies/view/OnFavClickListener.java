package com.example.iti_mvc.favMovies.view;

import android.widget.ImageView;

import com.example.iti_mvc.model.Movies;

public interface OnFavClickListener {
    void onFavClick(Movies movie, ImageView favImg);
}
