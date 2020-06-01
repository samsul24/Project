package com.bismillah.project.api.services;

import android.widget.EditText;

import com.bismillah.project.api.models.Envelope;
import com.bismillah.project.api.models.ResponeAllUser;
import com.bismillah.project.api.models.ResponeUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/users/")
    Call<ResponeUser> getUser(@Query("login") String login );
    @GET("/users")
    Call<Envelope<List<ResponeAllUser>>> getDataUser(@Query("page") EditText page);

}
