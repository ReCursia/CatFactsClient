package com.lunarekatze.catfacts.interfaces;

import com.lunarekatze.catfacts.models.Fact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://cat-fact.herokuapp.com/";

    //TODO 15 в константу лучше вывести, и почему только 15?
    @GET("facts/random?amount=15")
    Call<List<Fact>> getFacts();
}
