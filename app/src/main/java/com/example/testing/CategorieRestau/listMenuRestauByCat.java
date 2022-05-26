package com.example.testing.CategorieRestau;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_GProduit.ApiProduit;
import com.example.testing.CategorieRestau.Adapter.menuByCatRestau;
import com.example.testing.G_Panier.PanierActivity;
import com.example.testing.G_Produit.Adapter.MenuByCatAdapter;
import com.example.testing.G_Produit.CategorieActivity;
import com.example.testing.Models.MyDatabase;
import com.example.testing.Models.Produit;
import com.example.testing.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class listMenuRestauByCat extends AppCompatActivity {
    public static MyDatabase myDatabase;
    int id_cat;
    GridView simpleList;
    RelativeLayout rv_consult;
    FloatingActionButton goPanier;
    TextView cartcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_restau_by_cat);
        rv_consult=findViewById(R.id.rl_add_m);
        rv_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), CategorieActivity.class);
                startActivity(i);


            }
        });
        Intent intent=getIntent();
        if(intent.hasExtra("id_cat"))
        {
            id_cat=intent.getIntExtra("id_cat",2);
        }

        simpleList=(GridView) findViewById(R.id.gridViewListMenuRestau);

        // recuperation des categorie

        ApiProduit api= ApiClient.getClient().create(ApiProduit.class);
        System.out.println("jjjjjjjjjjjj");
        Call<List<Produit>> list = api.getProdByIdCat(id_cat);

        list.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Response<List<Produit>> response, Retrofit retrofit) {

                List<Produit> CatMenu=new ArrayList<Produit>();
                CatMenu=response.body();
                System.out.println("listtt "+CatMenu);
                menuByCatRestau menuAdapter=new menuByCatRestau(getApplicationContext(),R.layout.menurestauitem,CatMenu);
                simpleList.setAdapter(menuAdapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }



}