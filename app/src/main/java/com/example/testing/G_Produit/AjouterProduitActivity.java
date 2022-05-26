package com.example.testing.G_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Client.ApiClientMS;
import com.example.testing.Api.Api_GProduit.ApiProduit;
import com.example.testing.Api.Api_GProduit.ApiUnite;
import com.example.testing.Models.Unite;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.ID_RESTAU;

public class AjouterProduitActivity extends AppCompatActivity {
    public int id_restau=ID_RESTAU;
    public int id_cat,id_unite;
    ImageView imgBack;
    EditText edt_nomProd,edt_prixProd;
    String nomProd,prixProduit;
    float prixProd;
    RelativeLayout rl_app_choice,rl_submit_prod;
    RadioGroup rg;
    RadioButton rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);
        imgBack=(ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // recupére prix et le nom


        edt_nomProd=(EditText) findViewById(R.id.edt_nomProd);
        edt_prixProd=(EditText) findViewById(R.id.edt_prixProd);




        // recupére id cat

        Intent intent=getIntent();
        if(intent.hasExtra("id_cat"))
        {
            id_cat=intent.getIntExtra("id_cat",0);
        }

        // radio group

        rl_app_choice=(RelativeLayout) findViewById(R.id.rl_app_choice);
        rb= new RadioButton(this);
        rg=new RadioGroup(this);

        // traitement


        ApiUnite api = ApiClient.getClient().create(ApiUnite.class);
        Call<List<Unite>> listUnitByCat = api.getUnitByIdCat(this.id_cat);

        listUnitByCat.enqueue(new Callback<List<Unite>>() {
            @Override
            public void onResponse(Response<List<Unite>> response, Retrofit retrofit) {
                List<Unite> listUnit = new ArrayList<Unite>();
                if(response.isSuccess())
                {
                    listUnit =(List<Unite>) response.body();
                    System.out.println("liste unite "+listUnit.size());


                    int length= listUnit.size();
                    for (int i=0; i<length; i++)
                    {

                        rb= new RadioButton(getApplicationContext());
                        rb.setText(listUnit.get(i).getNomUnite());
                        rb.setId(listUnit.get(i).getId_unite());
                        rg.addView(rb);

                    }
                }
                rg.setOrientation(RadioGroup.HORIZONTAL);
                rl_app_choice.addView(rg);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        //checkeddd


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                id_unite=radioButton.getId();

                Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
            }
        });

        // go to image

        rl_submit_prod=(RelativeLayout) findViewById(R.id.rl_submit_prod);
        rl_submit_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nomProd= edt_nomProd.getText().toString();
                prixProduit= edt_prixProd.getText().toString();
                prixProd = Float.parseFloat(prixProduit);

                ApiProduit api = ApiClientMS.getClient().create(ApiProduit.class);
                Call<String> postProd = (Call<String>) api.postProduit(nomProd,id_restau,id_cat,prixProd,id_unite);

                postProd.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(),"Produit ajouté ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(),"Produit ajouté !", Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(),"Probléme lors de l ajout ", Toast.LENGTH_LONG).show();
                    }
                });

                Intent i = new Intent(getApplicationContext(), UploadImageActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}