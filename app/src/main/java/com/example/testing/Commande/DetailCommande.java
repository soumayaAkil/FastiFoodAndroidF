package com.example.testing.Commande;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Commande.ApiDcom;
import com.example.testing.Commande.Adapter.DcomAdapter;
import com.example.testing.Models.Dcommande;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DetailCommande extends AppCompatActivity {
    private RecyclerView rv_dcom;
    private RecyclerView.LayoutManager layoutManager;
    private DcomAdapter dcomAdapter;
    int id_com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_commande);
        Bundle extras = getIntent().getExtras();
        id_com= extras.getInt("id_com");




        ApiDcom api = ApiClient.getClient().create(ApiDcom.class);
        Call<List<Dcommande>> listDetByC=api.getdetailstByIdCom(id_com);
        listDetByC.enqueue(new Callback<List<Dcommande>>() {
            @Override
            public void onResponse(Response<List<Dcommande>> response, Retrofit retrofit) {
                List<Dcommande> listcom= new ArrayList<Dcommande>();
                if (response.isSuccess()) {
                    listcom=(List<Dcommande>)  response.body();

                    System.out.println("listtttttttttttttttttttttttttttttttttttttttttttttttt "+listcom);

                    // on récupére notre Recyclerview via son id
                    rv_dcom = findViewById(R.id.rcv_detcom);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    rv_dcom.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    dcomAdapter = new DcomAdapter(listcom, getApplicationContext());
                    rv_dcom.setAdapter(dcomAdapter);
                }}

            @Override
            public void onFailure(Throwable t) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        });

    }



//       System.out.println("response  " + response.body());
//                    System.out.println("liste detailcommande " + listt);
//    // on récupére notre Recyclerview via son id
//    rv_dcom = findViewById(R.id.rcv_detcom);
//    //on veut un recyclerview qui utilise un linearlayoutManager
//    layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
//                    rv_dcom.setLayoutManager(layoutManager);
//    //on donne notre adapter à notre recyclerview
//    dcomAdapter = new DcomAdapter(listt, getApplicationContext());
//                    rv_dcom.setAdapter(dcomAdapter);
}