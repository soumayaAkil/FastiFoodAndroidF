package com.example.testing.Commande.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Commande.ApiCom;
import com.example.testing.Commande.DetailCommande;
import com.example.testing.Commande.DetailFacteur;
import com.example.testing.Models.Dfacteur;
import com.example.testing.R;
import com.example.testing.Reclamation.AddReclamation;
import com.example.testing.Reclamation.ReclamationActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;


public class DfactAdapter extends RecyclerView.Adapter<DfactAdapter.DfactViewHolder> {
    List<Dfacteur> listDc;
    Context context;

    public DfactAdapter(List<Dfacteur> listDc, Context context) {
        this.listDc = listDc;
        this.context = context;
    }

    @NonNull
    @Override
    public DfactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_detail_facteur, parent, false);
        DfactAdapter.DfactViewHolder dfactViewHolder = new DfactAdapter.DfactViewHolder(view);
        return dfactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DfactAdapter.DfactViewHolder holder, int position) {


        Dfacteur DF = listDc.get(position);
        holder.reponse.setText(DF.getReponse());

        Picasso.get().load(BASE_URL_IMAGE+"uploads/"+DF.logo).into(holder.logo);


        holder.somme_Dfacteur.setText(Double.toString(DF.getSomme_Dfacteur()));
        holder.nomRestau.setText(DF.getNomRestau());
        System.out.println(DF.getReponse().equals("en attent"));
        if(DF.getReponse().equals("accepté")||DF.getReponse().equals("refusé")) {
            holder.annuler.setEnabled(false);
            holder.annuler.setClickable(false);
        }
        holder.annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.alert_annuler);
                Button dialogButton = (Button) dialog.findViewById(R.id.cancel2);
                Button dialogButtonA = (Button) dialog.findViewById(R.id.confirm2);

                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(context,"Cancel..!!",Toast.LENGTH_LONG).show();
                    }
                });
                // if button is clicked, close the custom dialog

                // if button is clicked, close the custom dialog
                dialogButtonA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ApiCom api = ApiClient.getClient().create(ApiCom.class);
                        Call<String> Acom = (Call<String>) api.AnnulerCom(DF.getId_com());
                        Acom.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                Toast.makeText(context,"Commande annulé ", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(context,"commande n'est pas annulé !", Toast.LENGTH_LONG).show();
                                // Toast.makeText(getApplicationContext(),"Probléme lors de l ajout ", Toast.LENGTH_LONG).show();
                            }
                        });
                        dialog.dismiss();
                        Intent intent =new Intent(context, DetailFacteur.class);
                        intent.putExtra("id_fact",DF.getId_fact());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);




                        // startAcctivity(new Intent(PlantsActivity.this, PayementActivity.class));
                    }
                });
                dialog.show();
            }
        });


        holder.rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, AddReclamation.class);
                context.startActivity(i);
            }
        });
        Dfacteur detcom = listDc.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listDc.get(position).getNomRestau(), Toast.LENGTH_LONG).show();
                Intent intent =new Intent(context, DetailCommande.class);
                intent.putExtra("id_com",detcom.getId_com());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return  listDc.size();
    }


    public class DfactViewHolder extends RecyclerView.ViewHolder {
        TextView nomRestau;
        TextView reponse;
        TextView  somme_Dfacteur,rec;
        ImageView logo,annuler;


        public DfactViewHolder(@NonNull View itemView) {
            super(itemView);
            nomRestau=itemView.findViewById(R.id.nomRest);
            reponse = itemView.findViewById(R.id.rep);
            somme_Dfacteur = itemView.findViewById(R.id.somme_Dfact);
            logo = itemView.findViewById(R.id.imageRest);
            annuler= itemView.findViewById(R.id.annD);
            rec=itemView.findViewById(R.id.recl);







        }
    }
}
