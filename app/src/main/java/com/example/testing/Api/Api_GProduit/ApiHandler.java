package com.example.testing.Api.Api_GProduit;

import com.example.testing.Models.Produit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiHandler {

    @GET("produit/GetAllRepas")
    Call<List<Produit>> getAllRepas();

    @GET("produit/GetAllDesserts")
    Call<List<Produit>> getAllDesserts();

    @GET("produit/GetAllBoissons")
    Call<List<Produit>> getAllBoissons();


    @GET("produit/images/:id_prod")
    Call<String> getPicture(@Query("id_prod") int id_prod);






}
