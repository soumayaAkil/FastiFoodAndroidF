package com.example.testing.G_Produit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.testing.AcceuilFragment;
import com.example.testing.ClientMenu.CommandeClientFragment;
import com.example.testing.Commande.CommandeFragment;
import com.example.testing.Profile.ProfileFragment;
import com.example.testing.PromotionP.PromotionFragment;
import com.example.testing.PromotionP.PromotionRestauFragment;
import com.example.testing.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(naviglistener);
    }
    public BottomNavigationView.OnNavigationItemSelectedListener naviglistener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment unFragment=null;
            switch (menuItem.getItemId())
            {
                case R.id.Acceuil:
                    unFragment= new AcceuilFragment();
                    break;
                case R.id.Promo:
                    unFragment= new PromotionRestauFragment();
                    break;
                case R.id.Commande:
                    unFragment= new CommandeClientFragment();

                    break;
                case R.id.Profile:
                    unFragment= new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplaceholder,unFragment).commit();
            return  true;
        }
    };
}