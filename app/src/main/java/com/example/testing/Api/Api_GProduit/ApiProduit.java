package com.example.testing.Api.Api_GProduit;

import com.example.testing.Models.Produit;
import com.example.testing.Models.Root;
import com.example.testing.Models.RootForRestau;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


public interface ApiProduit {

    @POST("/produit/postProd")
    Call<String> postProduit(@Query("nomProd") String nomProd,
                             @Query("id_restau") int id_restau,
                             @Query("id_cat") int id_cat,
                             @Query("prixProd") Float prixProd,
                             @Query("id_unite") int id_unite);

    @GET("produit/GetProdByIdCat/:id_cat")

    Call<List<Produit>> getProdByIdCat(@Query("id_cat") int id_cat);

    @GET("produit/images/:id_prod")
    Call<String> getPicture(@Query("id_prod") int id_prod);

    @GET("produit/GetProdByIdProd/:id_prod")
    Call<Root> getProdByIdProd(@Query("id_prod") int id_prod);

    @GET("detailProduit/GetPriceProd/:id_prod/:id_unite")
    Call<String> getPriceProd(@Query("id_prod") int id_prod,
                              @Query("id_unite") int id_unite);

    @GET("produit/FindNameRestByIdRest/:id_restau")
    Call<RootForRestau> getNomRestau(@Query("id_prod") int id_prod);
}
