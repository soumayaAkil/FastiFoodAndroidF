package com.example.testing.PromotionP;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_PromotionP.APIPromo;
import com.example.testing.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.ID_RESTAU;

public class addPromotion extends AppCompatActivity {
    public int id_restau = ID_RESTAU;
    public int id_cat, id_unite;
    ImageView imgBack;
    EditText edt_nomProd, edt_prixProd;
    String prixPromo;
    float prix_promo;
    RelativeLayout rl_submit_prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promotion);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // recupére prix
        edt_prixProd = (EditText) findViewById(R.id.edt_prix_promo);


        // go to image

        rl_submit_prod = (RelativeLayout) findViewById(R.id.rl_submit_promo);
        rl_submit_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prixPromo = edt_prixProd.getText().toString();
                prix_promo = Float.parseFloat(prixPromo);

                APIPromo api = ApiClient.getClient().create(APIPromo.class);
                Call<String> postProd = (Call<String>) api.postPromo(prix_promo, id_restau);
                postProd.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(), "Promotion ajouté ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(), "Promotion n'est pas ajouté !", Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(),"Probléme lors de l ajout ", Toast.LENGTH_LONG).show();
                    }
                });

                Intent i = new Intent(getApplicationContext(), UploadImagePromoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}