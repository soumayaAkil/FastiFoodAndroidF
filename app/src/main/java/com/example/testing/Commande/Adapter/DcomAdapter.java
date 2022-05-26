package com.example.testing.Commande.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.Dcommande;
import com.example.testing.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;

public class DcomAdapter extends RecyclerView.Adapter<DcomAdapter.DcomViewHolder>{
    List<Dcommande> listDc;
    Context context;
    public DcomAdapter(List<Dcommande> listDc, Context context) {
        this.listDc = listDc;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public DcomViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_detail_commande, parent, false);
        DcomAdapter.DcomViewHolder dcomViewHolder = new DcomAdapter.DcomViewHolder(view);
        return dcomViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DcomViewHolder holder, int position) {

        Dcommande DC = listDc.get(position);
        holder.quantite.setText(String.valueOf(DC.getQuantite()));
        holder.nomProd.setText(DC.getProduit());

        Picasso.get().load(BASE_URL_IMAGE+"uploads/"+DC.image_produit).into(holder.imageProd);
        holder.prix.setText(Double.toString(DC.getPrix()));
        holder.nomUnite.setText(DC.getUnite());
        Dcommande detcom = listDc.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, listDc.get(position).getProduit(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return listDc.size();
    }


    public class DcomViewHolder extends RecyclerView.ViewHolder {
        TextView quantite;
        TextView nomProd;
        TextView  nomUnite;
        TextView  prix;
        ImageView imageProd;
        public DcomViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            quantite=itemView.findViewById(R.id.quantite);
            nomProd = itemView.findViewById(R.id.nomProd);


            nomUnite = itemView.findViewById(R.id.unite);
            prix = itemView.findViewById(R.id.prixProd);
            imageProd= itemView.findViewById(R.id.imageProd);

        }
    }
}
