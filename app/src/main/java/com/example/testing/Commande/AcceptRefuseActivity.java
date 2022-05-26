package com.example.testing.Commande;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.testing.R;

public class AcceptRefuseActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rl_accept,rl_refuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_refuse);

        rl_accept= findViewById(R.id.rl_accept);
        rl_refuse= findViewById(R.id.rl_refuse);
        rl_accept.setOnClickListener(this);
        rl_refuse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Fragment unFragment=null;


        switch (v.getId()) {
            case R.id.rl_accept:
                rl_accept.setBackgroundResource(R.drawable.rectange_gradient);
                rl_refuse.setBackgroundResource(R.drawable.rectangle_gray);
                unFragment= new ComAcceptFragment();
                break;

            case R.id.rl_refuse:
                rl_refuse.setBackgroundResource(R.drawable.rectange_gradient);
                rl_accept.setBackgroundResource(R.drawable.rectangle_gray);
                unFragment= new ComRefuseFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplaceholder,unFragment).commit();


    }
}