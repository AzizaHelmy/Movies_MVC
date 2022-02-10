package com.example.iti_mvc.model;

import java.util.List;

public class GenerModel {
    private List<String> genres ;

    public GenerModel(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
