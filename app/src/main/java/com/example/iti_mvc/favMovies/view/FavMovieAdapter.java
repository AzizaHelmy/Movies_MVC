package com.example.iti_mvc.favMovies.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.iti_mvc.model.Movies;

import java.util.List;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.MovieViewHolder> {
    Context context;
    List<Movies> movies;
    OnFavClickListener favClickListener;

    public FavMovieAdapter(Context context, List<Movies> movies, OnFavClickListener favClickListener) {
        this.context = context;
        this.movies = movies;
        this.favClickListener = favClickListener;
    }
    public void setList(List<Movies> updatesMovies) {
        this.movies = updatesMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movies movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(movie.getReleaseYear() + "");
        holder.ratingBar.setRating(movie.getRating() / 2);
       // holder.tvGener.setText(movie.getGenre().toString());
        Glide.with(context).load(movie.getImage())
                .placeholder(R.drawable.holder)
                .into(holder.imgMovie);
        holder.removeFavImg.setOnClickListener(new View.OnClickListener() {
            // @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                // holder.imgFav.setImageResource(R.id.img_fav);
                favClickListener.onFavClick(movie);
            }
        });
        holder.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Hey this film is so amazing " + movie.getTitle());
                intent.setType("text/plain");
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(Intent.createChooser(intent, "Share With "));
                }
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
        ImageView removeFavImg;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imageView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingBar.setIsIndicator(true);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvGener = itemView.findViewById(R.id.tv_gener);
            imgShare = itemView.findViewById(R.id.img_share);
            removeFavImg = itemView.findViewById(R.id.img_fav2);
        }
    }
}
