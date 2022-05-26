package com.example.testing.Api.Api_PromotionP;


import com.example.testing.Models.Promotion;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface APIPromo {
    @POST("/promotion/postProd")
    Call<String> postPromo(@Query("prix_promo") Float prix_promo,
                           @Query("id_restau") int id_restau);
    @GET("promotion/GetlistPromo")
    Call<List<Promotion>> getListPromo();

    @GET("promotion/images/:id_promo")
    Call<String> getPicture(@Query("id_promo") int id_promo);
}
