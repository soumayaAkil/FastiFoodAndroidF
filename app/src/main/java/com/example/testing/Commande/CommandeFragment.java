package com.example.testing.Commande;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Commande.ApiCom;
import com.example.testing.Commande.Adapter.ComAdapter;
import com.example.testing.Models.commande;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CommandeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // recyclerview

    private RecyclerView rv_com;
    private RecyclerView.LayoutManager layoutManager;
    public ComAdapter comAdapter;
    private Spinner spinner;
    private int id_facteur ;
    private Button allButton;
    private Button FAujButton;
    private Button FAccButton;
    private Button FRefuseButton;
    private Button FPartielButton;
    public CommandeFragment() {
        // Required empty public constructor
    }

    public static CommandeFragment newInstance(String param1, String param2) {
        CommandeFragment fragment = new CommandeFragment();
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
        View v = inflater.inflate(R.layout.fragment_commande, container, false);



        ApiCom api = ApiClient.getClient().create(ApiCom.class);
        Call<List<commande>> listComByClient = api.getFactByIdClient(1);

        listComByClient.enqueue(new Callback<List<commande>>() {
            @Override
            public void onResponse(Response<List<commande>> response, Retrofit retrofit) {
                List<commande> listcom = new ArrayList<commande>();
                if(response.isSuccess())
                {
                    listcom =(List<commande>) response.body();
                    System.out.println("liste facteur "+listcom);


                    //int length= listcom.size();
                    // on récupére notre Recyclerview via son id
                    rv_com = v.findViewById(R.id.rv_com);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rv_com.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    comAdapter = new ComAdapter(listcom, getContext());
                    rv_com.setAdapter(comAdapter);
 /*                   for (int i=0; i<length; i++)
                    {

                        rb= new RadioButton(getContext());
                        rb.setText(listcom.get(i).mode_payement());
                        rb.setId(listcom.get(i).getId_unite());
                        rg.addView(rb);


                    } */
                }
                //  rg.setOrientation(RadioGroup.HORIZONTAL);
                //  rl_app_choice.addView(rg);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        //Repas
/*
        List<commande> listCom = new ArrayList<>();
        listCom.add(new commande(1, "9 rue kahra tunis", 1,500,"en cours","12/10/2020","à livré"));
        listCom.add(new commande(1, "10 rue kahra marsa", 1, 250,"en cours","12/11/2020","à livré"));
        listCom.add(new commande(1, "11 rue kahra ben arous", 1, 1, "en cours","10/10/2020","à livré"));
        listCom.add(new commande(1, "2 rue kahra ariana", 1, 100, "en cours","12/08/2020","à livré"));

*/
        //on sépare chaque ligne de notre liste par un trait
        /*
        DividerItemDecoration dividerItemDecoration;
        dividerItemDecoration = new DividerItemDecoration(rv_com.getContext(), DividerItemDecoration.VERTICAL);
        rv_com.addItemDecoration(dividerItemDecoration);
        */
        return v;

    }





















}