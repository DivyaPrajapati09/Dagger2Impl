package com.example.acquaint.daggerdemo.interfaces.network_interfaces;

import com.example.acquaint.daggerdemo.model.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
