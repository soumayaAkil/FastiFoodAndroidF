package com.example.testing.Calendrier.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;
import com.example.testing.Models.commande;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;

public class CMJAdapter extends RecyclerView.Adapter<CMJAdapter.cmjrViewHolder> {
    List<commande> listcj;
    Context context;
    public  CMJAdapter (List<commande> listcj, Context context) {
        this.listcj = listcj;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CMJAdapter.cmjrViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_cmj, parent, false);
        CMJAdapter.cmjrViewHolder cjViewHolder = new CMJAdapter.cmjrViewHolder(view);
        return cjViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CMJAdapter.cmjrViewHolder holder, int position) {

        commande DF = listcj.get(position);
        holder.heure.setText(DF.getHeure());
        Picasso.get().load(BASE_URL_IMAGE+"uploads/"+DF.image_produit).into(holder.imageproduit);
        holder.prix.setText(String.valueOf(DF.getPrix()));
        holder.nomUnite.setText(DF.getUnite());
        holder.quantite.setText(String.valueOf(DF.getQuantite()));
        holder.nomProd.setText(DF.getProduit());



    }

    @Override
    public int getItemCount() {
        return  listcj.size();
    }

    public class cmjrViewHolder extends RecyclerView.ViewHolder  {
        TextView heure;
        ImageView imageproduit;
        TextView quantite;
        TextView nomProd;
        TextView  nomUnite;
        TextView  prix;

        public cmjrViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            heure=itemView.findViewById(R.id.HR);
            imageproduit=itemView.findViewById(R.id.imageProd);
            quantite=itemView.findViewById(R.id.quantite);
            nomProd = itemView.findViewById(R.id.nomProd);


            nomUnite = itemView.findViewById(R.id.unite);
            prix = itemView.findViewById(R.id.prixProd);

        }
    }
}
