package com.example.testing.Api.API_Reclamation;


import com.example.testing.Models.Reclamations;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiRec {
    //get reclamation du restau
    @GET("Reclamations/GetAllRecalamtionRestau")
    Call<List<Reclamations>> GetRecalamtions();
    //get type reclamations
    @GET("/Reclamations/GetATypeRec")
    Call<List<Reclamations>> GetTRecalamtions();
    //post reclamation
    @POST("Reclamations/post")
    Call<String> PostReclamation(@Query("id_client") int id_client,
                                 @Query("id_com") int id_com,
                                 @Query("id_TR") int id_TR,
                                 @Query("publish") int publish);
    //Post Type reclamation
    @POST("Reclamations/postATR")
    Call<String> PostTR(@Query("Designation") String Designation,
                        @Query("Responsable") String Responsable,
                        @Query("Description") String Description);

    //Post autre reclamation
    @POST("Reclamations/PostAutreReclamation")
    Call<String> postAutreReclamation(
            @Query("id_client") int id_client,
            @Query("id_com") int id_com,
            @Query("publish") int publish);

}
