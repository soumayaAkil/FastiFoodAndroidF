package com.example.testing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.testing.Api.Api_Client.ApiClient;
import com.example.testing.Api.Api_GProduit.ApiCategorie;
import com.example.testing.G_Produit.Adapter.CatMenuAdapter;
import com.example.testing.Models.Categorie;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int id_restau=1;
    GridView simpleList;
    List<Categorie> CatList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListCatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListCatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCatFragment newInstance(String param1, String param2) {
        ListCatFragment fragment = new ListCatFragment();
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
       View v= inflater.inflate(R.layout.fragment_list_cat, container, false);

        simpleList=(GridView) v.findViewById(R.id.gridViewListCatClient);


        // recuperation des categorie

        ApiCategorie api= ApiClient.getClient().create(ApiCategorie.class);
        Call<List<Categorie>> list = api.getCatByIdRestau(id_restau);
        list.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Response<List<Categorie>> response, Retrofit retrofit) {
                if(response.isSuccess())
                {
                    List<Categorie> CatList=new ArrayList<Categorie>();
                    CatList=response.body();
                    System.out.println("listtt "+CatList);
                    CatMenuAdapter catMenuAdapter=new CatMenuAdapter(getContext(),R.layout.catmenugriditem,CatList);
                    simpleList.setAdapter(catMenuAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failure ");
            }
        });


       return v;
    }
}