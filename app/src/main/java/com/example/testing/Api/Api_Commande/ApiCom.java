package com.example.testing.Api.Api_Commande;

import com.example.testing.Models.commande;
import com.example.testing.Models.commandeRestau;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiCom {

    @GET("facteur/GetAllFacteursByIdClient/:id_client")
    Call<List<commande>> getFactByIdClient(@Query("id_client") int id_client);
    @POST("commande/AnnulerCom")
    Call<String> AnnulerCom(@Query("id_com") int id_com);
    @GET("facteur/GetFacturesAccepte/:id_client")
    Call<List<commande>> getFactByIdClientAcc(@Query("id_client") int id_client);
    @GET("facteur/GetFacturesPartiel/:id_client")
    Call<List<commande>> getFactByIdClientPar(@Query("id_client") int id_client);
    @GET("facteur/GetFacturesRefuse/:id_client")
    Call<List<commande>> getFactByIdClientRef(@Query("id_client") int id_client);
    @GET("facteur/commandesbydate/:date")
    Call<List<commande>> getComtBydate(@Query("date") String date);

    @GET("commande/GetCommandesAcceptByIdRestau/:id_restau")
    Call<List<commandeRestau>> getCommandesAcceptByIdRestau(@Query("id_restau") int id_restau);
    @GET("commande/GetCommandesRefuseByIdRestau/:id_restau")
    Call<List<commandeRestau>> getCommandesRefuseByIdRestau(@Query("id_restau") int id_restau);





}