package com.lunarekatze.catfacts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://cat-fact.herokuapp.com/";

    @GET("facts")
    Call<List<Fact>> getFacts();
}
