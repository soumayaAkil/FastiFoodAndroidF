package com.example.testing.ClientMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Api.Api_Client.ApiClientMS;
import com.example.testing.Api.Api_GProduit.ApiHandler;
import com.example.testing.G_Produit.Adapter.ProduitAdapter;
import com.example.testing.G_Produit.ListCategorieClientActivity;
import com.example.testing.Models.Produit;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AcceuilClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcceuilClientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rv_repas;
    private RecyclerView rv_boissons;
    private RecyclerView rv_desserts;
    private RecyclerView.LayoutManager layoutManager;
    private ProduitAdapter produitAdapter;

    private TextView tv_voir_cat;
    public AcceuilClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AcceuilClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AcceuilClientFragment newInstance(String param1, String param2) {
        AcceuilClientFragment fragment = new AcceuilClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_acceuil_client, container, false);

        tv_voir_cat=v.findViewById(R.id.tv_voir_cat);
        tv_voir_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListCategorieClientActivity.class);
                startActivity(i);
            }
        });
        //menu

        ApiHandler api= ApiClientMS.getClient().create(ApiHandler.class);
        //Repas
        Call<List<Produit>> listRepas=api.getAllRepas();
        listRepas.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Response<List<Produit>> response, Retrofit retrofit) {
                List<Produit> listRep = new ArrayList<Produit>();
                if(response.isSuccess())
                {
                    listRep=(List<Produit>) response.body();
                    // on récupére notre Recyclerview via son id
                    rv_repas=v.findViewById(R.id.rv_repas);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                    rv_repas.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    produitAdapter = new ProduitAdapter(listRep, getContext());
                    rv_repas.setAdapter(produitAdapter);
                    //on sépare chaque ligne de notre liste par un trait
                    DividerItemDecoration dividerItemDecoration;
                    dividerItemDecoration = new DividerItemDecoration(rv_repas.getContext(),DividerItemDecoration.VERTICAL);
                    rv_repas.addItemDecoration(dividerItemDecoration);

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Error"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //List<Produit> listRepas = new ArrayList<>();
        /*listRepas.add(new Produit(1,"Lasagne",R.drawable.lasagne,1,1));
        listRepas.add(new Produit(1,"Tagliatelle",R.drawable.tagliatelle,1,1));
        listRepas.add(new Produit(1,"Spaghetti",R.drawable.spaghetti,1,1));
        listRepas.add(new Produit(1,"Spaghetti",R.drawable.spaghetti,1,1));*/




        // boissons

        Call<List<Produit>> listBoissons=api.getAllBoissons();
        listBoissons.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Response<List<Produit>> response, Retrofit retrofit) {
                List<Produit> listBoi = new ArrayList<Produit>();
                if(response.isSuccess())
                {
                    listBoi=(List<Produit>) response.body();
                    // on récupére notre Recyclerview via son id
                    rv_boissons=v.findViewById(R.id.rv_boissons);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                    rv_boissons.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    produitAdapter = new ProduitAdapter(listBoi, getContext());
                    rv_boissons.setAdapter(produitAdapter);
                    //on sépare chaque ligne de notre liste par un trait
                    DividerItemDecoration dividerItemDecoration;
                    dividerItemDecoration = new DividerItemDecoration(rv_boissons.getContext(),DividerItemDecoration.VERTICAL);
                    rv_boissons.addItemDecoration(dividerItemDecoration);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Error"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
        //List<Produit> listBoissons = new ArrayList<>();
       /* listBoissons.add(new Produit(1,"Jus de fraise",R.drawable.jusfraise,1,1));
        listBoissons.add(new Produit(1,"Jus d'orange",R.drawable.jusorange,1,1));
        listBoissons.add(new Produit(1,"Gazeuse",R.drawable.gazeuse,1,1));
        listBoissons.add(new Produit(1,"Gazeuse",R.drawable.coca,1,1));*/





        // desserts

        // List<Produit> listDesserts = new ArrayList<>();
       /* listDesserts.add(new Produit(1,"Tarte",R.drawable.tartes,1,1));
        listDesserts.add(new Produit(1,"Cheesecake",R.drawable.cheesecake,1,1));
        listDesserts.add(new Produit(1,"Verrine",R.drawable.verrine,1,1));
        listDesserts.add(new Produit(1,"Verrine",R.drawable.verrine,1,1));*/

        Call<List<Produit>> listDesserts=api.getAllDesserts();
        listDesserts.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Response<List<Produit>> response, Retrofit retrofit) {
                List<Produit> listDess = new ArrayList<Produit>();
                if(response.isSuccess())
                {
                    listDess=(List<Produit>) response.body();
                    // on récupére notre Recyclerview via son id
                    rv_desserts=v.findViewById(R.id.rv_desserts);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                    rv_desserts.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    produitAdapter = new ProduitAdapter(listDess, getContext());
                    rv_desserts.setAdapter(produitAdapter);
                    //on sépare chaque ligne de notre liste par un trait
                    DividerItemDecoration dividerItemDecoration;
                    dividerItemDecoration = new DividerItemDecoration(rv_desserts.getContext(),DividerItemDecoration.VERTICAL);
                    rv_desserts.addItemDecoration(dividerItemDecoration);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        return v;
    }
}