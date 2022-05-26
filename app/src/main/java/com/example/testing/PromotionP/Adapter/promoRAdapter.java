package com.example.testing.PromotionP.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_PromotionP.APIPromo;
import com.example.testing.Models.Promotion;
import com.example.testing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;


public class promoRAdapter extends ArrayAdapter {
    List<Promotion> PromoListt= new ArrayList<>();
    Context context;
    public promoRAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        PromoListt= objects;
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
        v = inflater.inflate(R.layout.promogriditemrest, null);
        TextView textView = (TextView) v.findViewById(R.id.titrePromoItemR);
        ImageView imageViewR = (ImageView) v.findViewById(R.id.imagePromoItemR);
        textView.setText(String.valueOf(PromoListt.get(position).getPrix_promo()) + "  DT");

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    /*
                    Intent i=new Intent(getContext(),ListMenuByIdCatActivity.class);
                    int id_cat=PromoList.get(position).getId_cat();
                    i.putExtra("id_cat",id_cat);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(i);

                    */

            }
        });
        APIPromo api= ApiClient.getClient().create(APIPromo.class);
        Call<String> pic = api.getPicture(PromoListt.get(position).getId_promo());
        pic.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                String picture=response.body();
                System.out.println(PromoListt.get(position).getPhoto_promo());
                System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                Picasso.get().load(BASE_URL_IMAGE+"uploads/"+picture).into(imageViewR);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        return v;

    }
}


