package com.example.testing.G_Produit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_GProduit.ApiProduit;
import com.example.testing.G_Panier.PanierActivity;
import com.example.testing.G_Produit.Adapter.MenuByCatAdapter;
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

public class ListMenuByIdCatActivity extends AppCompatActivity {

    public static MyDatabase myDatabase;
    int id_cat;
    GridView simpleList;
    FloatingActionButton goPanier;
    TextView cartcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_by_id_cat);

        // recupére id cat

        Intent intent=getIntent();
        if(intent.hasExtra("id_cat"))
        {
            id_cat=intent.getIntExtra("id_cat",0);
        }

        simpleList=(GridView) findViewById(R.id.gridViewListMenuClient);

        // recuperation des categorie

        ApiProduit api= ApiClient.getClient().create(ApiProduit.class);
        Call<List<Produit>> list = api.getProdByIdCat(id_cat);
        list.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Response<List<Produit>> response, Retrofit retrofit) {

                List<Produit> CatMenu=new ArrayList<Produit>();
                CatMenu=response.body();
                System.out.println("listtt "+CatMenu);
                MenuByCatAdapter menuByCatAdapter=new MenuByCatAdapter(getApplicationContext(),R.layout.menubycatitem,CatMenu);
                simpleList.setAdapter(menuByCatAdapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        goPanier=(FloatingActionButton) findViewById(R.id.shopp_button);
        cartcount=(TextView)findViewById(R.id.cartcount);
        goPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PanierActivity.class);
                startActivity(i);
            }
        });

        myDatabase= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"My_Cart").allowMainThreadQueries().build();

        updatacartcount();
    }

    private void updatacartcount() {
        if (cartcount==null)return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myDatabase.cartDao().countCart()==0)
                    cartcount.setVisibility(View.INVISIBLE);
                else {
                    cartcount.setVisibility(View.VISIBLE);
                    cartcount.setText(String.valueOf(myDatabase.cartDao().countCart()));
                }
            }
        });

  }
}