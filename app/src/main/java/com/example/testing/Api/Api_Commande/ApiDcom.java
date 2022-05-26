package com.example.testing.Api.Api_Commande;

import com.example.testing.Models.Dcommande;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface ApiDcom {

    @GET("commandesP/GetAllcommandesPA/:id_com")
    Call<List<Dcommande>> getdetailstByIdCom(@Query("id_com") int id_com);
}
