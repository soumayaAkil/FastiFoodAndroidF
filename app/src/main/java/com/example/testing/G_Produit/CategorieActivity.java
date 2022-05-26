package com.example.testing.G_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_GProduit.ApiCategorie;
import com.example.testing.G_Produit.AjouterProduitActivity;
import com.example.testing.Models.Categorie;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.ID_RESTAU;

public class CategorieActivity extends AppCompatActivity {

    public int id_restau=ID_RESTAU,id_cat;
    RelativeLayout rl_submit_cat,rl_app_choice;
    RadioGroup rg;
    RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        // radio group

        rl_app_choice=(RelativeLayout) findViewById(R.id.rl_app_choice);
        rb= new RadioButton(this);
        rg=new RadioGroup(this);

        // traitement

        ApiCategorie api= ApiClient.getClient().create(ApiCategorie.class);
        Call<List<Categorie>> listCatByRestau=api.getCatByIdRestau(this.id_restau);
        listCatByRestau.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Response<List<Categorie>> response, Retrofit retrofit) {

                List<Categorie> listCat = new ArrayList<Categorie>();

                if(response.isSuccess())
                {
                    listCat=(List<Categorie>) response.body();

                    int length= listCat.size();
                    for (int i=0; i<length; i++)
                    {
                        rb= new RadioButton(getApplicationContext());
                        rb.setText(listCat.get(i).getNomCat());
                        rb.setId(listCat.get(i).getId_cat());
                        rg.addView(rb);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "failed 1 ", Toast.LENGTH_LONG).show();

                }
                rg.setOrientation(RadioGroup.VERTICAL);
                rl_app_choice.addView(rg);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "failed "+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


        //checkeddd


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                id_cat=radioButton.getId();

                Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
            }
        });

        // navigation
        rl_submit_cat=(RelativeLayout) findViewById(R.id.rl_submit_cat);
        rl_submit_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("id categorie "+id_cat);
                Intent i= new Intent(getApplicationContext(), AjouterProduitActivity.class);
                i.putExtra("id_cat",id_cat);
                startActivity(i);
            }
        });
    }
}