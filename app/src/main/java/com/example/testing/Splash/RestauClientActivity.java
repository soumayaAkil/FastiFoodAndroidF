package com.example.testing.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testing.ClientMenu.MenuClientActivity;
import com.example.testing.G_Produit.MenuActivity;
import com.example.testing.R;

public class RestauClientActivity extends AppCompatActivity {

    private Button btn_Restau,btn_Client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restau_client);
        btn_Client=findViewById(R.id.buttonClient);
        btn_Client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuClientActivity.class);
                startActivity(i);
            }
        });
        btn_Restau=findViewById(R.id.buttonRestau);
        btn_Restau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });
    }
}