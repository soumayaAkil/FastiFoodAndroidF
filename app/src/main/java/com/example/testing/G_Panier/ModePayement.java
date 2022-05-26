package com.example.testing.G_Panier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testing.R;

public class ModePayement extends AppCompatActivity {
    private Button btnModePayement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_payement);
        btnModePayement=(Button) findViewById(R.id.btnMP);
        //Vers page remplissage du formulaire
        btnModePayement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), FUcommande.class);
                startActivity(i);
            }
        });
    }
}