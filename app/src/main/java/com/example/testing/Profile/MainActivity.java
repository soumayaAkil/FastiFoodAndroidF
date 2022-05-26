package com.example.testing.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testing.G_Produit.CategorieActivity;
import com.example.testing.G_Produit.DetailProdActivity;
import com.example.testing.G_Produit.ListCategorieClientActivity;
import com.example.testing.G_Produit.MenuActivity;
import com.example.testing.R;


public class MainActivity extends AppCompatActivity {

    Button gomenu;
    Button godetail;
    Button goadd,goTest,ListCat;



  public static final String BASE_URL_IMAGE="http://172.16.13.46:5000/";
  // public static final String BASE_URL_IMAGE="http://172.16.13.97:5000/";
  public static final String BASE_URL_IMAGE_MS="http://172.16.13.46:5050/";
    public static final int ID_RESTAU=1;
    Button gocalendar;
    Button goreclamtion;
    Button SelectMultiple ,catRestau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        gomenu=(Button) findViewById(R.id.goMenu);
        gomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);

            }
        });
        godetail=(Button)findViewById(R.id.goDetail);
        godetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), DetailProdActivity.class);
                startActivity(i);
            }
        });

        goadd=(Button) findViewById(R.id.goAddProd);
        goadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), CategorieActivity.class);
                startActivity(i);
            }
        });

        ListCat=(Button) findViewById(R.id.goListCat);
        ListCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), ListCategorieClientActivity.class);
                startActivity(i);
            }
        });

    }
}