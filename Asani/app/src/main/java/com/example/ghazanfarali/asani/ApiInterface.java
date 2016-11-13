package com.example.ghazanfarali.asani;

/**
 * Created by ghazanfarali on 09/10/2016.
 */


import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("index.php")
    Call<Collection<ServiceData>> getTopRatedMovies();

    @POST("index.php")
    Call<Collection<ServiceData>> createUser(@Body ServiceData user);
}