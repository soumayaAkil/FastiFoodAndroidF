package com.example.testing.Reclamation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Api.API_Reclamation.ApiRec;
import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Models.Reclamations;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddReclamation extends AppCompatActivity {
    RelativeLayout rl_app_choice;
    RadioGroup rg;
    RadioButton rb;
    public int id_tr,id_com;
    Button reclame;
    TextView autreTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reclamation);
        rl_app_choice=(RelativeLayout) findViewById(R.id.choiceTR);
        rb= new RadioButton(this);
        rg=new RadioGroup(this);
        ApiRec api= ApiClient.getClient().create(ApiRec.class);
        Call<List<Reclamations>> listTreclam=api.GetTRecalamtions();
        listTreclam.enqueue(new Callback<List<Reclamations>>() {
            @Override
            public void onResponse(Response<List<Reclamations>> response, Retrofit retrofit) {

                List<Reclamations> listRec = new ArrayList<Reclamations>();

                if(response.isSuccess())
                {
                    listRec=(List<Reclamations>) response.body();

                    int length= listRec.size();
                    for (int i=0; i<length; i++)
                    {
                        rb= new RadioButton(getApplicationContext());
                        rb.setText(listRec.get(i).getType_Reclamation());
                        rb.setId(listRec.get(i).getId_TypeReclamation());
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
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                id_tr=radioButton.getId();

                Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
            }
        });
        Intent intent=getIntent();
        if(intent.hasExtra("id_com"))
        {
            id_com=intent.getIntExtra("id_com",0);
        }
        reclame=(Button ) findViewById(R.id.sendreclm);
        reclame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiRec api = ApiClient.getClient().create(ApiRec.class);
                Call<String> postReclmt = (Call<String>) api.PostReclamation(1,id_com,id_tr,0);
                postReclmt.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(),"Reclamation ajouté ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(),"Reclamation ajouté!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        autreTR=(TextView)findViewById(R.id.Arcl);
        autreTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), AjouterAREcActivity.class);
                i.putExtra("id_com",id_com);

                startActivity(i);

            }
        });


    }
}