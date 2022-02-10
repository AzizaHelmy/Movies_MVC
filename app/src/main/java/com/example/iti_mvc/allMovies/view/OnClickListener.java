package com.example.iti_mvc.allMovies.view;

import com.example.iti_mvc.model.Movies;

public interface OnClickListener {
    void onFavClicked(Movies movie);
    void onShareClicked(Movies movie);
}
