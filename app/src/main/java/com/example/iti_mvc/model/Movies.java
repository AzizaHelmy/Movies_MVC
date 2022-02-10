package com.example.iti_mvc.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "movies")
public class Movies implements Serializable {
    @PrimaryKey
    @NonNull
    private String title;
    private String image;
    private Float rating;
    private Integer releaseYear;
    private boolean fav;
    @Ignore
    //private List<GenerModel> genre;
    private final static long serialVersionUID = 648610031873363397L;
    @Ignore
    private List<String> genre = null;
//    public List<GenerModel> getGenre() {
//        return genre;
//    }
//
//    public void setGenre(List<GenerModel> genre) {
//        this.genre = genre;
//    }

    public Movies(@NonNull String title, String image, Float rating, Integer releaseYear, boolean fav, List<GenerModel> genre) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.fav = fav;
        //this.genre = genre;
    }

    public Movies() {
    }



    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }


}