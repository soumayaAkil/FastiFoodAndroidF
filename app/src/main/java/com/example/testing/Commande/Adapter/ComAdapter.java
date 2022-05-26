package com.example.testing.Commande.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Coursier.APICoursier;
import com.example.testing.Commande.DetailFacteur;
import com.example.testing.Models.Coursier;
import com.example.testing.Models.commande;
import com.example.testing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;


public class ComAdapter extends RecyclerView.Adapter<ComAdapter.ComViewHolder> {

    List<commande> listCom;
    Context context;

    public ComAdapter(List<commande> listCom, Context context) {
        this.listCom = listCom;
        this.context = context;
    }


    @NonNull
    @Override
    public ComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lc_item_commande, parent, false);
        ComAdapter.ComViewHolder comViewHolder = new ComAdapter.ComViewHolder(view);
        return comViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComViewHolder holder, int position) {

        commande com = listCom.get(position);
        holder.modepaye.setText(com.getMode_payement());
        holder.date.setText(com.getDate().substring(0,10));
        holder.somme_com.setText(Double.toString(com.getSomme_fact()));
        holder.adresse.setText(com.getAdresse());
        holder.heure.setText(com.getHeure());
        holder.etat.setText(com.getEtat());
        if(com.getStatus().equals("en cours") ) {
            //holder.pBar.setProgress(50);
            holder.pBar.setIndeterminate(true);
        }
        if(com.getStatus().equals("livré") ) {
            holder.pBar.setProgress(100);
            holder.pBar.setIndeterminate(false);
        }
        if(com.getStatus().equals("pas encore") ) {
            holder.pBar.setProgress(0);
            holder.pBar.setIndeterminate(false);
        }
        holder.pBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APICoursier api = ApiClient.getClient().create(APICoursier.class);
                Call<List<Coursier>> Acom = api.getCoursierByIdF(com.getId_fact());
                Acom.enqueue(new Callback<List<Coursier>>() {
                    @Override
                    public void onResponse(Response<List<Coursier>> response, Retrofit retrofit) {
                        List<Coursier> listcour = new ArrayList<Coursier>();
                        if(response.isSuccess()) {
                            listcour = (List<Coursier>) response.body();
                            System.out.println("liste facteur " + listcour.get(0).nomCoursier);

                            Toast.makeText(context, "Détail coursier ", Toast.LENGTH_LONG).show();
                            final Dialog dialog = new Dialog(v.getContext());
                            dialog.setContentView(R.layout.coursier_alert);
                            Button dialogButton = (Button) dialog.findViewById(R.id.cancel2);
                            TextView nom=(TextView) dialog.findViewById(R.id.textViewTitre);
                            TextView num=(TextView)dialog.findViewById(R.id.num);
                            ImageView imgc=(ImageView)dialog.findViewById(R.id.imgC);
                            nom.setText(listcour.get(0).nomCoursier+" "+ listcour.get(0).prenomCoursier);
                            num.setText(listcour.get(0).numCoursier);

                            Picasso.get().load( BASE_URL_IMAGE+"uploads/"+listcour.get(0).imageCoursier).into(imgc);

                         
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Toast.makeText(context,"Cancel..!!",Toast.LENGTH_LONG).show();
                                }
                            });
                            dialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(context,"commande n'est pas annulé !", Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(),"Probléme lors de l ajout ", Toast.LENGTH_LONG).show();
                    }
                });



            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listCom.get(position).getAdresse(), Toast.LENGTH_LONG).show();
                Intent intent =new Intent(context, DetailFacteur.class);
                intent.putExtra("id_fact",com.getId_fact());
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
        TextView status;
        TextView adresse;
        TextView  somme_com;
        TextView date;
        TextView modepaye;
        TextView heure;
        TextView etat;
        ProgressBar pBar;

        public ComViewHolder(@NonNull View itemView) {
            super(itemView);
            modepaye=itemView.findViewById(R.id.modepaye);
            heure=itemView.findViewById(R.id.heure);
            etat=itemView.findViewById(R.id.etat);
            pBar= itemView.findViewById(R.id.p_Bar);
            adresse = itemView.findViewById(R.id.adresse);
            date = itemView.findViewById(R.id.date);
            somme_com = itemView.findViewById(R.id.somme_com);


        }

    }



}