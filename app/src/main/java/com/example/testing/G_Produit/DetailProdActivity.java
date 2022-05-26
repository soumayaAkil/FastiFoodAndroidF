package com.example.testing.G_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_GProduit.ApiHandler;
import com.example.testing.Api.Api_GProduit.ApiProduit;
import com.example.testing.Api.Api_GProduit.ApiUnite;
import com.example.testing.G_Panier.PanierActivity;
import com.example.testing.Models.Cart;
import com.example.testing.Models.Root;
import com.example.testing.Models.RootForRestau;
import com.example.testing.Models.Unite;
import com.example.testing.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.BASE_URL_IMAGE;

public class DetailProdActivity extends AppCompatActivity {

    String prix="20",imageProd,nomProd,price;
    //Float prixTotal=0f;
    RootForRestau nomRest;
    int id_prod,id_cat,id_unite;
    RelativeLayout rl_submit_cat,rl_app_choice;
    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prod);

        // recupére id cat

        Intent intent=getIntent();
        if(intent.hasExtra("id_prod"))
        {
            id_prod=intent.getIntExtra("id_prod",0);
        }
        if(intent.hasExtra("id_cat"))
        {
            id_cat=intent.getIntExtra("id_cat",0);
        }

        // radio buttonn
        rl_app_choice=(RelativeLayout) findViewById(R.id.rl_app_choice);
        rb= new RadioButton(this);
        rg=new RadioGroup(this);

        // traitement

        // traitement


        ApiUnite apiUnite = ApiClient.getClient().create(ApiUnite.class);
        Call<List<Unite>> listUnitByCat = apiUnite.getUnitByIdCat(this.id_cat);

        listUnitByCat.enqueue(new Callback<List<Unite>>() {
            @Override
            public void onResponse(Response<List<Unite>> response, Retrofit retrofit) {
                List<Unite> listUnit = new ArrayList<Unite>();
                if(response.isSuccess())
                {
                    listUnit =(List<Unite>) response.body();
                    System.out.println("liste unite "+listUnit.size());


                    int length= listUnit.size();
                    for (int i=0; i<length; i++)
                    {

                        rb= new RadioButton(getApplicationContext());
                        rb.setText(listUnit.get(i).getNomUnite());
                        rb.setId(listUnit.get(i).getId_unite());
                        rg.addView(rb);

                    }
                }
                rg.setOrientation(RadioGroup.VERTICAL);
                rl_app_choice.addView(rg);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });





        // image

        ShapeableImageView imageView=(ShapeableImageView) findViewById(R.id.imgDetailProd);
        ApiHandler api=ApiClient.getClient().create(ApiHandler.class);
        Call<String> pic = api.getPicture(id_prod);

        pic.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                String picture=response.body();
                imageProd=picture;
               
                Picasso.get().load(BASE_URL_IMAGE+"uploads/"+picture).into(imageView);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(" fl", "failed");
            }
        });



        // recupére nom

        TextView tv_nomprod=(TextView) findViewById(R.id.tv_nomProd);
        ApiProduit apiProd=ApiClient.getClient().create(ApiProduit.class);
        Call<Root> nomProds = apiProd.getProdByIdProd(id_prod);
        nomProds.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Response<Root> response, Retrofit retrofit) {
                Root nomProduit = response.body();
                nomProd=nomProduit.nomProd;
                tv_nomprod.setText(nomProd);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failure au niveau de la rec du nom : "+t);
            }
        });
        //checkeddd


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                id_unite=radioButton.getId();

                System.out.println("iddd"+id_unite);
                ApiProduit apiPro=ApiClient.getClient().create(ApiProduit.class);
                Call<String> prixProd = apiPro.getPriceProd(id_prod,id_unite);
                prixProd.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {


                        if(response.isSuccess())
                        {
                            prix=response.body();
                             price=prix+" DT";
                            TextView tv_prixProd=(TextView) findViewById(R.id.tv_prixProd);
                            tv_prixProd.setText(price);
                        }

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("failure "+t);
                    }
                });
                Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
            }
        });


        // recuperation nom restau


        Call<RootForRestau> name=apiProd.getNomRestau(id_prod);
        name.enqueue(new Callback<RootForRestau>() {
            @Override
            public void onResponse(Response<RootForRestau> response, Retrofit retrofit) {
                nomRest=response.body();
                System.out.println("restau"+nomRest.designation);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        // go to panier

        RelativeLayout rv_submit=(RelativeLayout) findViewById(R.id.rl_submit_go_to_panier);
        rv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent=new Intent(getApplicationContext(), PanierActivity.class);
                //intent.putExtra("prixTotal",prixTotal);



                Cart cart=new Cart();
                cart.setId_prod(id_prod);
                cart.setImageProd(imageProd);
                cart.setNomRest(nomRest.designation);
                cart.setNomProd(nomProd);
                cart.setPrixProd(prix);
                cart.setQuantite(1);
                if (ListMenuByIdCatActivity.myDatabase.cartDao().isAddToCart(id_prod)!=1){
                    ListMenuByIdCatActivity.myDatabase.cartDao().addToCart(cart);
                    Intent i = new Intent(getApplicationContext(),PanierActivity.class);
                    startActivity(i);
                    Toast.makeText(DetailProdActivity.this, "Added to cart!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DetailProdActivity.this, "You are Already added to cart!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        
    }

}