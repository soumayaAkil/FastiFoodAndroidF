package com.example.testing.Commande.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.commandeRestau;
import com.example.testing.R;

import java.util.List;

public class ComAdapterAcceptRefuse extends RecyclerView.Adapter<ComAdapterAcceptRefuse.ComViewHolder> {
    List<commandeRestau> listCom;
    Context context;

    public ComAdapterAcceptRefuse(List<commandeRestau> listCom, Context context) {
        this.listCom = listCom;
        this.context = context;
    }


    @NonNull
    @Override
    public ComAdapterAcceptRefuse.ComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_accept_refuse, parent, false);
        ComAdapterAcceptRefuse.ComViewHolder comViewHolder = new ComAdapterAcceptRefuse.ComViewHolder(view);
        return comViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComViewHolder holder, int position) {
        commandeRestau com = listCom.get(position);
        holder.modepaye.setText(com.getMode_payement());
        holder.date.setText(com.getDate().substring(0,15));
        String somme= String.valueOf((com.getSomme_fact()));
        holder.somme_com.setText(somme);
        holder.adresse.setText(com.getAdresse());

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
        ImageView annuler;
        ProgressBar pBar;

        public ComViewHolder(@NonNull View itemView) {
            super(itemView);
            modepaye=itemView.findViewById(R.id.modepaye);
            pBar= itemView.findViewById(R.id.p_Bar);

            adresse = itemView.findViewById(R.id.adresse);
            date = itemView.findViewById(R.id.date);
            somme_com = itemView.findViewById(R.id.somme_com);


        }

    }

}
