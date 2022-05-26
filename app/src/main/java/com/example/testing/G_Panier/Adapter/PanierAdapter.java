package com.example.testing.G_Panier.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.testing.Models.Cart;
import com.example.testing.Models.MyDatabase;
import com.example.testing.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.PanierViewHolder>{
    List<Cart> listPanier;
    Context context;
    Float   prixTotal = 0f;
    public static MyDatabase myDatabase;
    int id_prod,qnt;
    int qtttt=1;
    int qnttt;
    int qntttt;

    public PanierAdapter(List<Cart> listPanier, Context context)
    {
        this.listPanier=listPanier;
        this.context=context;
    }
    @NonNull
    @Override
    public PanierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.panier_item,parent,false);
        PanierAdapter.PanierViewHolder panierViewHolder= new PanierAdapter.PanierViewHolder(view);
        return panierViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PanierViewHolder holder, int position) {
        myDatabase= Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class,"My_Cart").allowMainThreadQueries().build();
        Cart panier=listPanier.get(position);

        Integer pr=Integer.parseInt(panier.getPrixProd());
        int qntpp=myDatabase.cartDao().getqntapr(panier.getId_prod());
        int prr=pr*qntpp;
        prixTotal+=prr;


        Intent intent = new Intent("custom-message");
        intent.putExtra("prr",prixTotal);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        //holder.imageProd.setImageResource(panier.getImageProd());
        holder.nomProd.setText(panier.getNomProd());
        holder.designation.setText(panier.getNomRest());


//if(qntpp>=1){
        holder.quantite.setText(String.valueOf(panier.getQuantite()));
        Integer price=Integer.parseInt(panier.getPrixProd());
        int qntppp=myDatabase.cartDao().getqntapr(panier.getId_prod());
        holder.prixprod.setText(String.valueOf(price)+" DT");
//}else {
        //  holder.quantite.setText(String.valueOf("1"));
        // holder.prixprod.setText(panier.getPrixProd()+"DT");

//}

        //incremente quantite

        holder.incrQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase.cartDao().getqntapr(panier.getId_prod());
                myDatabase.cartDao().incrquantity(myDatabase.cartDao().getqntapr(panier.getId_prod()), panier.getId_prod());
                holder.quantite.setText(String.valueOf( myDatabase.cartDao().getqntapr(panier.getId_prod())));
                Integer prixprod=Integer.parseInt(panier.getPrixProd());
                Integer qntapr = myDatabase.cartDao().getqntapr(panier.getId_prod());
                int  prxx= prixprod*qntapr;
                holder.prixprod.setText(String.valueOf(prixprod)+" DT");
                Integer pr=Integer.parseInt(panier.getPrixProd());
                prixTotal+=pr;

                Intent intent = new Intent("custom-message");
                intent.putExtra("prr",prixTotal);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
        //decremente quantite

            System.out.println("AFTER IF ");
            holder.decrQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myDatabase.cartDao().getqntapr(panier.getId_prod());
                    myDatabase.cartDao().decrquantity(myDatabase.cartDao().getqntapr(panier.getId_prod()), panier.getId_prod());
                    holder.quantite.setText(String.valueOf( myDatabase.cartDao().getqntapr(panier.getId_prod())));
                    Integer prixprod=Integer.parseInt(panier.getPrixProd());
                    Integer qntapr = myDatabase.cartDao().getqntapr(panier.getId_prod());
                    int  prxx= prixprod*qntapr;
                    holder.prixprod.setText(String.valueOf(prixprod)+" DT");
                    prixTotal-=pr;
                    Intent intent = new Intent("custom-message");
                    intent.putExtra("prr",prixTotal);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            });


        // holder.quantite.setText((panier.getQuantite());
        Picasso.get().load(BASE_URL_IMAGE+"uploads/"+panier.getImageProd()).into(holder.imageProd);


    }

    // public Float getPrixTotal() {return prixTotal;}


    @Override
    public int getItemCount() {
        return listPanier.size();
    }

    public class PanierViewHolder extends  RecyclerView.ViewHolder{
        ShapeableImageView imageProd;
        ImageView incrQ,decrQ;
        TextView designation,nomProd,prixprod,quantite;
        public PanierViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProd=itemView.findViewById(R.id.imageProd);
            designation=itemView.findViewById(R.id.nomRest);
            nomProd=itemView.findViewById(R.id.nomProd);
            prixprod=itemView.findViewById(R.id.prixProd);
            quantite=itemView.findViewById(R.id.quantite);
            incrQ =itemView.findViewById(R.id.addProd);
            decrQ = itemView.findViewById(R.id.decProd);

        }
    }
}
