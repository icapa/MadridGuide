package com.example.icapa.madridguide.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.adapters.ShopsAdapter;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;


public class ShopsFragment extends Fragment {
    private Shops shops;
    private RecyclerView shopsRecyclerView;

    private ShopsAdapter adapter;

    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops,container,false);
        shopsRecyclerView = (RecyclerView) view.findViewById(R.id.shops_recycler_view);
        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public Shops getShops() {
        return shops;
    }


    private void updateUI() {


        adapter = new ShopsAdapter(shops,getActivity());
        shopsRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new ShopsAdapter.OnElementClick() {
            @Override
            public void clickedOn(Shop shop, int position) {
                // TODO: show shop detail
            }
        });

    }

    public void setShops(Shops shops) {
        this.shops = shops;
        updateUI();
    }

}