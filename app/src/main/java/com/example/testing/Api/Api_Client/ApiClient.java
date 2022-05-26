package com.example.testing.Api.Api_Client;

import android.util.Log;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApiClient {




    private static final String BASE_URL="http://172.16.12.91:5000/";
 //   private static final String BASE_URL="http://172.16.13.46:5000/";





    private static Retrofit retrofit =null;


    public static Retrofit getClient()
    {
        System.out.println("hello URL"+BASE_URL);
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
