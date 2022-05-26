package com.example.testing.Commande.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Commande.ApiComR;
import com.example.testing.Commande.DetailCommande;
import com.example.testing.Profile.MainActivity;
import com.example.testing.Models.commandeRestau;
import com.example.testing.R;


import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class comRAdapter extends RecyclerView.Adapter<comRAdapter.ComViewHolder>{


    List<commandeRestau> listCom;
    Context context;
    // private selectmultipleActivity mainActivity;


    public comRAdapter(List<commandeRestau> listCom, Context context) {

        this.listCom = listCom;
        this.context = context;
        //this.mainActivity=(selectmultipleActivity ) context;





    }


    @NonNull
    @Override
    public ComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lc_item_commande_rest, parent, false);
        comRAdapter.ComViewHolder comViewHolder = new comRAdapter.ComViewHolder(view);
        return comViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComViewHolder holder, int position) {
/*
        if(mainActivity.position == position){
            holder.checkBox.setChecked(true);
            mainActivity.position = -1;
        }

        if(mainActivity.isActionMode){
            Anim anim = new Anim (100,holder.linearLayout);
            anim.setDuration(300);
            holder.linearLayout.setAnimation(anim);
        }else {
            Anim anim = new Anim (0,holder.linearLayout);
            anim.setDuration(300);
            holder.linearLayout.setAnimation(anim);
            holder.checkBox.setChecked(false);
        }
        holder.cardView.setOnLongClickListener(v -> {
            mainActivity.startSelection(position);

            return true;
        });
        holder.checkBox.setOnClickListener(v -> {
            mainActivity.check(v,position);
        } );
*/
        commandeRestau com = listCom.get(position);
        holder.modepaye.setText(com.getMode_payement());
        holder.date.setText(com.getDate().substring(0,15));
        holder.somme_com.setText(Double.toString(com.getSomme_fact()));
        holder.adresse.setText(com.getAdresse());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.array_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(com.getReponse());
        holder.spinner.setSelection(spinnerPosition);
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chaine="en attent";
                String repo = String.valueOf(holder.spinner.getSelectedItem());
                com.setReponse(String.valueOf(adapter.getItem(position)));
                if (position == 0) {
                    holder.spinner.setBackgroundResource(R.drawable.attent);
                    chaine="en attent";
                    //Toast.makeText(context, "Selected : " + repo, Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    chaine="accepté";
                    holder.spinner.setBackgroundResource(R.drawable.accept);
                    //Toast.makeText(context, "Selected : " + repo, Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    chaine="refusé";
                    holder.spinner.setBackgroundResource(R.drawable.refuse);
                    //Toast.makeText(context, "Selected : " + repo, Toast.LENGTH_SHORT).show();
                }

                ApiComR api = ApiClient.getClient().create(ApiComR.class);
                Call<String> putRep = (Call<String>) api.putRep(com.getId_fact(),chaine);

                putRep.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        // Toast.makeText(context,"reponse modifié ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(context,"reponse n'est pas modifié !", Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(),"Probléme lors de l ajout ", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listCom.get(position).getAdresse(), Toast.LENGTH_LONG).show();
                Intent intent =new Intent(context, DetailCommande.class);
                intent.putExtra("id_com",com.getId_fact());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        {
            return listCom.size();
        }
    }


    public class ComViewHolder extends RecyclerView.ViewHolder {
        Spinner spinner;
        TextView adresse;
        TextView somme_com;
        TextView date;
        TextView modepaye;
        CheckBox checkBox;
        private MainActivity mainActivity;
        private LinearLayout linearLayout;
        private ConstraintLayout cardView;



        public ComViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            adresse = itemView.findViewById(R.id.adresseR);
            date = itemView.findViewById(R.id.dateR);
            somme_com = itemView.findViewById(R.id.somme_comR);
            modepaye = itemView.findViewById(R.id.modepayeR);
            spinner = itemView.findViewById(R.id.rl_submit_catR);
            this.mainActivity = mainActivity;

            linearLayout = itemView.findViewById(R.id.linearLayout);
            checkBox = itemView.findViewById(R.id.checkbox);





            // When user select a List-Item
        }




    }
    class Anim extends Animation {
        private int width,startWidth;
        private View view;
        public Anim (int width,View view){
            this.width= width;
            this.view= view;
            this.startWidth= view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newWidth = startWidth + (int) ((width-startWidth) * interpolatedTime);
            view.getLayoutParams().width = newWidth;
            view.requestLayout();

            super.applyTransformation(interpolatedTime, t);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }




}


