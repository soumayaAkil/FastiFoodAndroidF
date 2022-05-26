package com.example.testing.CategorieRestau;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_GProduit.ApiCategorie;
import com.example.testing.CategorieRestau.Adapter.CatMenuRAdapter;
import com.example.testing.G_Produit.Adapter.CatMenuAdapter;
import com.example.testing.Models.Categorie;
import com.example.testing.R;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.ID_RESTAU;

public class ListCategorieCRestauActivity extends AppCompatActivity {
    int id_restau=ID_RESTAU;
    GridView simpleList;
    List<Categorie> CatList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categorie_c_restau);

        simpleList=(GridView) findViewById(R.id.gridViewListCatRestau);


        // recuperation des categorie

        ApiCategorie api= ApiClient.getClient().create(ApiCategorie.class);
        Call<List<Categorie>> list = api.getCatByIdRestau(id_restau);
        list.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Response<List<Categorie>> response, Retrofit retrofit) {
                if(response.isSuccess())
                {
                    List<Categorie> CatList=new ArrayList<Categorie>();
                    CatList=response.body();
                    System.out.println("listtt "+CatList);
                    CatMenuRAdapter catMenuRAdapter=new CatMenuRAdapter(getApplicationContext(),R.layout.catmenugriditemr,CatList);
                    simpleList.setAdapter(catMenuRAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failure ");
            }
        });


    }
}