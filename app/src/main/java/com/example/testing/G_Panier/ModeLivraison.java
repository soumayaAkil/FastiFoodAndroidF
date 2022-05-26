package com.example.testing.G_Panier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testing.R;

public class ModeLivraison extends AppCompatActivity {
    private Button btnModeLivraison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_livraison);
        btnModeLivraison=(Button)findViewById(R.id.btnML);
        //Vers page mode de payement
        btnModeLivraison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), ModePayement.class);
                startActivity(i);
            }
        });
    }
}