package com.example.tugasrumah8;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rvMovie;
    MovieAdapter adapter;
    MovieViewModel viewModel;
    ProgressBar progressBar;
    SwipeRefreshLayout srl;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovie = findViewById(R.id.rvMovie);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        srl = findViewById(R.id.swipe);
        srl.setOnRefreshListener(this);

        final int orientation = getResources().getConfiguration().orientation;
        int kolom;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            kolom = 1;
        } else {
            kolom = 2;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, kolom);
        rvMovie.setLayoutManager(gridLayoutManager);
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovies().observe(this, new Observer<MovieList>() {
            @Override
            public void onChanged(MovieList movies) {
                if (movies.getResults() != null) {
                    adapter = new MovieAdapter(MainActivity.this, movies.getResults());
                    adapter.notifyDataSetChanged();
                    rvMovie.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Error: ", Toast.LENGTH_LONG).show();
                }

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onRefresh() {
        srl.setRefreshing(true);
        viewModel.Refresh();
        srl.setRefreshing(false);
    }
}
