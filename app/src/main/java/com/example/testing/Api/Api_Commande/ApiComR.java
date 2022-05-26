package com.example.testing.Api.Api_Commande;

import com.example.testing.Models.commandeRestau;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;

public interface ApiComR {
    @GET("commande/GetAllCommandesByIdRestau/:id_restau")
    Call<List<commandeRestau>> getFactByIdRestau(@Query("id_restau") int id_restau);

    @POST("commande/putReponse")
    Call<String> putRep(@Query("id_com") int id_com,
                        @Query("reponse") String reponse);
}
