package com.example.codechallenge043019.View;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.example.codechallenge043019.Model.Movie;
import com.example.codechallenge043019.R;
import com.example.codechallenge043019.ViewModel.CustomViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private CustomViewModel viewModel;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private List<Movie> stdMovieList;
    private MutableLiveData<List<Movie>> movieListLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        viewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        movieListLiveData = viewModel.getObservable();

        movieListLiveData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                stdMovieList = movies;
                displayMovies(movies, null);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                displayMovies(stdMovieList, s);
                return true;
            }
        });

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "10 minutes update");
                displayMovies(stdMovieList, null);
            }
        };
        timer.schedule(timerTask, 0 , 600000);

    }

    public void displayMovies(List<Movie> movies, String searchString) {
        List<Movie> movieList = new ArrayList<>();
        if (searchString != null){
            for (Movie m: movies){
                if (m.getTitle().toLowerCase().contains(searchString.toLowerCase()) ||
                        m.getGenre().toLowerCase().contains(searchString.toLowerCase())){
                    movieList.add(m);
                }
            }
        }
        else{
            movieList = movies;
        }
        List<List<Movie>> movieRowList = createMovieRowList(movieList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        CustomViewAdapter customViewAdapter = new CustomViewAdapter(this, movieRowList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customViewAdapter);
    }

    private List<List<Movie>> createMovieRowList(List<Movie> movieList) {
        List<List<Movie>> movieRowList = new ArrayList<>();
        List<Movie> currentList = null;
        for(int i = 0; i < movieList.size(); i++){
            if (i%3 == 0){
                movieRowList.add(new ArrayList<Movie>());
                currentList = movieRowList.get(movieRowList.size()-1);
            }
            currentList.add(movieList.get(i));
        }

        return movieRowList;
    }
}
