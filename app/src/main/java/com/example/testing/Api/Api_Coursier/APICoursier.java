package com.example.testing.Api.Api_Coursier;

import com.example.testing.Models.Coursier;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface APICoursier {
    @GET("coursier/GetCoursiersByFact/:id_fact")
    Call<List<Coursier>> getCoursierByIdF(@Query("id_fact") int id_fact);
}
