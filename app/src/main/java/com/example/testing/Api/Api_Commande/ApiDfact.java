package com.example.testing.Api.Api_Commande;

import com.example.testing.Models.Dfacteur;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiDfact {

    @GET("commande/GetAllCommandesByIdFact/:id_fact")
    Call<List<Dfacteur>> getComtByIdFact(@Query("id_fact") int id_fact);

}
