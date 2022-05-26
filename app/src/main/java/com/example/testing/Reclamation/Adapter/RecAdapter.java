package com.example.testing.Reclamation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.Reclamations;
import com.example.testing.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.reclamationViewHolder>{
    List<Reclamations> listrec;
    Context context;

    public RecAdapter(List<Reclamations> listrec, Context context) {
        this.listrec = listrec;
        this.context = context;

    }

    @NonNull
    @NotNull
    @Override
    public reclamationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lc_item_reclamation, parent, false);
        RecAdapter.reclamationViewHolder recViewHolder = new RecAdapter.reclamationViewHolder(view);
        return recViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull reclamationViewHolder holder, int position) {
        Reclamations recl = listrec.get(position);
        holder.Type.setText(recl.getType_Reclamation());
        holder.Description.setText(recl.getDescription_Reclamation());
        holder.quantite.setText(String.valueOf(recl.getQuantite_produit()));
        holder.produit.setText(recl.getNomProd());
        holder.unite.setText(recl.getUnite_produit());
        holder.somme.setText("|"+recl.getSomme_commande()+"DT");
        holder.nomclient.setText(recl.getNom_client());
        holder.prenomclient.setText(recl.getPrenom_client());
        holder.adresseclient.setText("|"+recl.getAdresse_client());


    }

    @Override
    public int getItemCount() {
        return listrec.size();
    }


    public class reclamationViewHolder extends RecyclerView.ViewHolder {
        TextView Type;
        TextView Description;
        TextView quantite;
        TextView produit;
        TextView unite;
        TextView somme;
        TextView nomclient;
        TextView prenomclient;
        TextView adresseclient;

        public reclamationViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            Type=itemView.findViewById(R.id.Type);
            Description = itemView.findViewById(R.id.Description);
            quantite = itemView.findViewById(R.id.qnt);
            produit = itemView.findViewById(R.id.prd);
            unite = itemView.findViewById(R.id.unit);
            somme = itemView.findViewById(R.id.SMCMD);
            nomclient = itemView.findViewById(R.id.nclient);
            prenomclient = itemView.findViewById(R.id.pnclient);
            adresseclient = itemView.findViewById(R.id.adrclient);


        }
    }
}
