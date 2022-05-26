package com.example.testing.Api.Api_GProduit;

import com.example.testing.Models.Unite;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiUnite {
    @GET("unite/GetUnitByIdCat/:id_cat")
    Call<List<Unite>> getUnitByIdCat(@Query("id_cat") int id_cat);
}
