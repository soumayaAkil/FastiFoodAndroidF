package com.example.testing.ClientMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Commande.ApiComR;
import com.example.testing.Calendrier.CalendarActivity;
import com.example.testing.Commande.AcceptRefuseActivity;
import com.example.testing.Commande.Adapter.comRAdapter;
import com.example.testing.G_Produit.CategorieActivity;
import com.example.testing.Models.commandeRestau;
import com.example.testing.R;
import com.example.testing.Reclamation.ReclamationActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.ID_RESTAU;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommandeClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommandeClientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RelativeLayout   rv_consult,rv_rec,rv_cln;
    private RecyclerView rv_comR;
    private RecyclerView.LayoutManager layoutManager;
    private com.example.testing.Commande.Adapter.comRAdapter comRAdapter;
    boolean isContextualModeEnabled=false;

    public CommandeClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommandeClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommandeClientFragment newInstance(String param1, String param2) {
        CommandeClientFragment fragment = new CommandeClientFragment();
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
        View v = inflater.inflate(R.layout.fragment_commande_client, container, false);
        rv_consult=v.findViewById(R.id.rl_go_com);
        rv_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent( getActivity(), AcceptRefuseActivity.class);
                startActivity(i);


            }
        });
        rv_rec=v.findViewById(R.id.rl_go_rec);
        rv_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ReclamationActivity.class);
                startActivity(i);


            }
        });
        rv_cln=v.findViewById(R.id.rl_go_calendrier);
        rv_cln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), CalendarActivity.class);
                startActivity(i);


            }
        });


        ApiComR api = ApiClient.getClient().create(ApiComR.class);
        Call<List<commandeRestau>> listComByRes = api.getFactByIdRestau(ID_RESTAU);
        System.out.println(listComByRes);
        listComByRes.enqueue(new Callback<List<commandeRestau>>() {
            @Override
            public void onResponse(Response<List<commandeRestau>> response, Retrofit retrofit) {
                List<commandeRestau> listCom = new ArrayList<commandeRestau>();
                if (response.isSuccess()) {
                    listCom = (List<commandeRestau>) response.body();
                    System.out.println("liste commande restau " + listCom);



/*

        //Repas

        List<commandeRestau> listCom = new ArrayList<>();
        listCom.add(new commandeRestau(1, "9 rue kahra tunis", 1,50, "en attent","en cours","12/10/2020","à livré"));
        listCom.add(new commandeRestau(1, "10 rue kahra marsa", 1, 250, "en attent","en cours","07/12/2020","à livré"));
        listCom.add(new commandeRestau(1, "11 rue kahra ben arous", 1, 1, "en attent","en cours","18/10/2020","à livré"));
        listCom.add(new commandeRestau(1, "2 rue kahra ariana", 1, 100, "en attent","en cours","17/09/2020","à livré"));

*/



                    // on récupére notre Recyclerview via son id
                    rv_comR = v.findViewById(R.id.rv_comR);
                    rv_comR.setHasFixedSize(true);
                    //on veut un recyclerview qui utilise un linearlayoutManager
                    layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rv_comR.setLayoutManager(layoutManager);
                    //on donne notre adapter à notre recyclerview
                    comRAdapter = new comRAdapter(listCom, getContext());
                    rv_comR.setAdapter(comRAdapter);


                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        return v;

    }

}