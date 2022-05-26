package com.example.testing.G_Produit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_GProduit.ApiCategorie;
import com.example.testing.G_Produit.ListMenuByIdCatActivity;
import com.example.testing.Models.Categorie;
import com.example.testing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;


public class CatMenuAdapter extends ArrayAdapter {
    List<Categorie> CatList= new ArrayList<>();
    Context context;

    public CatMenuAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        CatList= objects;
        context=context;

    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.catmenugriditem, null);
        TextView textView = (TextView) v.findViewById(R.id.titreCatItem);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageCatItem);
        textView.setText(CatList.get(position).getNomCat());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), ListMenuByIdCatActivity.class);
                int id_cat=CatList.get(position).getId_cat();
                i.putExtra("id_cat",id_cat);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(i);

            }
        });
        ApiCategorie api= ApiClient.getClient().create(ApiCategorie.class);
        Call<String> pic = api.getPicture(CatList.get(position).getId_cat());
        pic.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                String picture=response.body();

                Picasso.get().load(BASE_URL_IMAGE+"uploads/"+picture).into(imageView);
             
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        return v;

    }
}
