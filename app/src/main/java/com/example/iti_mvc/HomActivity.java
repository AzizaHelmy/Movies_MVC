package com.example.iti_mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iti_mvc.allMovies.controller.MainActivity;
import com.example.iti_mvc.favMovies.controller.FavMovieActivity;

public class HomActivity extends AppCompatActivity {
    Button buttAllMovies;
    Button buttFav;
    Button buttExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom);
        initializeView();
        buttAllMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moviesIntent=new Intent(getBaseContext(), MainActivity.class);
                startActivity(moviesIntent);
            }
        });
        buttFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favmoviesIntent=new Intent(getBaseContext(), FavMovieActivity.class);
                startActivity(favmoviesIntent);
            }
        });
        buttExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initializeView() {
        buttAllMovies=findViewById(R.id.butt_allMovies);
        buttFav=findViewById(R.id.butt_fav);
        buttExit=findViewById(R.id.butt_exit);
    }
}