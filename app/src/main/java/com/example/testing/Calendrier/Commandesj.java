package com.example.testing.Calendrier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Commande.ApiCom;
import com.example.testing.Calendrier.Adapter.CMJAdapter;
import com.example.testing.R;
import com.example.testing.Models.commande;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class Commandesj extends AppCompatActivity {
    private RecyclerView rv_cmj;
    private RecyclerView.LayoutManager layoutManager;
    private CMJAdapter cmjAdapter;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandesj);
        Bundle extras = getIntent().getExtras();
        date= extras.getString("date");


        ApiCom api = ApiClient.getClient().create(ApiCom.class);

        Call<List<commande>> listComByD = api.getComtBydate(date);


        listComByD.enqueue(new Callback<List<commande>>() {
            @Override
            public void onResponse(Response<List<commande>> response, Retrofit retrofit) {
                List<commande> list = new ArrayList<commande>();
                if (response.isSuccess()) {
                    list = (List<commande>) response.body();
                    System.out.println("liste commande " + list);




/*
        //Repas

        List<Dfacteur> list = new ArrayList<>();
        list.add(new Dfacteur(1, "KFC", "accepté", 100, baguette, 1));
        list.add(new Dfacteur(1, "Baguette Baguette", "accepté", 100, baguette, 1));
        list.add(new Dfacteur(1, "Mix Max", "accepté", 100, baguette, 1));
*/

                    // on récupére notre Recyclerview via son id
                    rv_cmj = findViewById(R.id.rv_Dfact);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    rv_cmj.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    cmjAdapter = new CMJAdapter(list, getApplicationContext());
                    rv_cmj.setAdapter(cmjAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("erreur");

            }

        });
    }
}
