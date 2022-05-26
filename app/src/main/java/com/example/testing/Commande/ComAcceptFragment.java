package com.example.testing.Commande;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_Commande.ApiCom;
import com.example.testing.Models.commandeRestau;
import com.example.testing.R;
import com.example.testing.Commande.Adapter.ComAdapterAcceptRefuse;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.testing.Profile.MainActivity.ID_RESTAU;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComAcceptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComAcceptFragment extends Fragment {

    int id_restau=ID_RESTAU;
    private RecyclerView rv_com;
    private RecyclerView.LayoutManager layoutManager;
    private ComAdapterAcceptRefuse comAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComAcceptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComAcceptFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComAcceptFragment newInstance(String param1, String param2) {
        ComAcceptFragment fragment = new ComAcceptFragment();
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
        View v=inflater.inflate(R.layout.fragment_com_accept, container, false);

        ApiCom api = ApiClient.getClient().create(ApiCom.class);
        Call<List<commandeRestau>> list=api.getCommandesAcceptByIdRestau(id_restau);
        list.enqueue(new Callback<List<commandeRestau>>() {
            @Override
            public void onResponse(Response<List<commandeRestau>> response, Retrofit retrofit) {

                List<commandeRestau> CommList=new ArrayList<commandeRestau>();
                CommList=response.body();

                // on récupére notre Recyclerview via son id
                rv_com = v.findViewById(R.id.rv_com);
                //on veut un recyclerview qui utilise un linearlayoutManager
                layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                rv_com.setLayoutManager(layoutManager);
                //on donne notre adapter à notre recyclerview
                comAdapter = new ComAdapterAcceptRefuse(CommList, getContext());
                rv_com.setAdapter(comAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "failuree "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }
}