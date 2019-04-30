package com.example.codechallenge043019.Model;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("api/movies")
    Observable<MovieList> getMovieList();
}
