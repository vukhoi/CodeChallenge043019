package com.example.codechallenge043019.Model;

import com.example.codechallenge043019.Model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    @SerializedName("data")
    @Expose
    private List<Movie> data = null;

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

}