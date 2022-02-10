package com.example.iti_mvc.allMovies.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iti_mvc.R;
import com.example.iti_mvc.db.MovieDAO;
import com.example.iti_mvc.db.Repository;
import com.example.iti_mvc.model.Movies;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    Context context;
    List<Movies> movies;
    OnClickListener clickListener;
    boolean flag = false;

    public MovieAdapter(Context context, List<Movies> movies, OnClickListener clickListener) {
        this.context = context;
        this.movies = movies;
        this.clickListener = clickListener;
    }

    public void setList(List<Movies> updatesMovies) {
        this.movies = updatesMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_rv, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movies movie = movies.get(position);

        if (movie.isFav()) {
            holder.imgFav.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else{
            holder.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(movie.getReleaseYear() + "");
        holder.ratingBar.setRating(movie.getRating() / 2);
        //  holder.tvGener.setText(movie.getGenre().toString());
        Glide.with(context).load(movie.getImage())
                .placeholder(R.drawable.holder)
                .into(holder.imgMovie);
        holder.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onShareClicked(movie);

            }
        });
        holder.imgFav.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")

            @Override
            public void onClick(View view) {
                // Drawable img= holder.imgFav.getDrawable();
//                if (!flag) {
//                    holder.imgFav.setImageResource(R.drawable.ic_baseline_favorite_24);
//                    flag = true;
//                    clickListener.onFavClicked(movie);
//                } else {
//
//                    holder.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//                    flag = false;
//                }

                clickListener.onFavClicked(movie);
                //add  this item in db //Noooo Logic Here
            }
        });
    }

    //=======================================================
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //===========================================================
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        RatingBar ratingBar;
        TextView tvTitle;
        TextView tvYear;
        TextView tvGener;
        ImageView imgShare;
        ImageView imgFav;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imageView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingBar.setIsIndicator(true);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvGener = itemView.findViewById(R.id.tv_gener);
            imgShare = itemView.findViewById(R.id.img_share);
            imgFav = itemView.findViewById(R.id.img_fav);
            imgFav.setTag("fav");
        }
    }
}
