package com.example.testing.CategorieRestau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.testing.Api.Api_Client.ApiClientMS;
import com.example.testing.Api.Api_Commande.ApiCom;
import com.example.testing.Api.Api_GProduit.ApiProduit;
import com.example.testing.CategorieRestau.ListCategorieCRestauActivity;
import com.example.testing.Commande.DetailFacteur;
import com.example.testing.G_Produit.DetailProdActivity;
import com.example.testing.Models.Produit;
import com.example.testing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE_MS;

public class menuByCatRestau extends ArrayAdapter {
    List<Produit> MenuByCatList= new ArrayList<>();
    public menuByCatRestau(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        MenuByCatList= objects;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ImageView annuler;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.menurestauitem, null);
        TextView textView = (TextView) v.findViewById(R.id.titreMenuItemR);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageProdR);
        textView.setText(MenuByCatList.get(position).getNomProd());
        annuler= (ImageView) v.findViewById(R.id.supp);

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.alert_supprimer);
                Button dialogButton = (Button) dialog.findViewById(R.id.cancel2);
                Button dialogButtonA = (Button) dialog.findViewById(R.id.confirm2);

                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      /*  dialog.dismiss();
                        Toast.makeText(getContext(),"Cancel..!!",Toast.LENGTH_LONG).show();

                       */
                    }
                });
                // if button is clicked, close the custom dialog

                // if button is clicked, close the custom dialog
                dialogButtonA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
/*
                        Toast.makeText(getContext(),"Commande annulé ", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent intent =new Intent(getContext(), ListCategorieCRestauActivity.class);

                        getContext().startActivity(intent);
                        */


                    }
                });
                dialog.show();
            }
        });







        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), DetailProdActivity.class);
                int id_prod=MenuByCatList.get(position).getId_prod();
                int id_cat=MenuByCatList.get(position).getId_cat();
                i.putExtra("id_prod",id_prod);
                i.putExtra("id_cat",id_cat);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(i);

            }
        });


        ApiProduit api= ApiClientMS.getClient().create(ApiProduit.class);
        Call<String> pic = api.getPicture(MenuByCatList.get(position).getId_prod());
        pic.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                String picture=response.body();

                Picasso.get().load(BASE_URL_IMAGE_MS+"uploads/"+picture).into(imageView);
                //    Picasso.get().load("http://172.16.23.70:5000/images/"+picture).into(imageView);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        return v;

    }

}
