package com.bismillah.project.api.services;

import com.bismillah.project.api.models.ItemResponse;
import com.bismillah.project.api.models.ResponeUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
//    @GET("/users/{user}")
//    Call<List<ResponeAllUser>> getUsers(@Query("page") int page);

    @GET("/search/users")
    Call<ResponeUser> getUser(@Query("q")String userValue);

    @GET("/search/users?q=language:java+location:lagos")
    Call<ItemResponse> getItems(@Query("page") int page);
}
