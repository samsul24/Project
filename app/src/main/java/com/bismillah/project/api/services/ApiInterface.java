package com.bismillah.project.api.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/users")
    Call<Response> getUser(@Query("login") String login );
}