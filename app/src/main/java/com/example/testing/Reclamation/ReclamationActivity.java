package com.example.testing.Reclamation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.testing.Api.API_Reclamation.ApiRec;
import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Models.Reclamations;
import com.example.testing.R;
import com.example.testing.Reclamation.Adapter.RecAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ReclamationActivity extends AppCompatActivity {
    private TextView Rec;
    private RelativeLayout rv_rec_add;
    private RecyclerView reclamationRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);
        Rec = findViewById(R.id.TxtvREC);

        reclamationRecyclerView = findViewById(R.id.RcvReclamtions);
        ApiRec api = ApiClient.getClient().create(ApiRec.class);
        Call<List<Reclamations>> listRec = api.GetRecalamtions();
        System.out.println("Reclamations" + listRec);
        listRec.enqueue(new Callback<List<Reclamations>>() {
            @Override
            public void onResponse(Response<List<Reclamations>> response, Retrofit retrofit) {
                List<Reclamations> list = new ArrayList<Reclamations>();

                if (response.isSuccess()) {

                    list = (List<Reclamations>) response.body();
                    System.out.println("liste Reclamations" + list);


                    // on récupére notre Recyclerview via son id
                    reclamationRecyclerView = findViewById(R.id.RcvReclamtions);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    reclamationRecyclerView.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    recAdapter = new RecAdapter(list, getApplicationContext());
                    reclamationRecyclerView.setAdapter(recAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }

        });


    }

    @Override
    protected void onResume() {

        super.onResume();

    }
}
