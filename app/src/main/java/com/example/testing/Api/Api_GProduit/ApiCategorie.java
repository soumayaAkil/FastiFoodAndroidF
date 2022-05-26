package com.example.testing.Api.Api_GProduit;

import com.example.testing.Models.Categorie;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiCategorie {


    @GET("categoie/GetCatByIdRestau/:id_restau")
    Call<List<Categorie>> getCatByIdRestau(@Query("id_restau") int id_restau);

    @GET("categoie/images/:id_cat")
    Call<String> getPicture(@Query("id_cat") int id_cat);
}
