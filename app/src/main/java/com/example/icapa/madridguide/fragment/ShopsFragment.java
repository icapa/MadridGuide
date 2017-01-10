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
import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.model.AnyTopics;
import com.example.icapa.madridguide.views.OnElementClick;


public class ShopsFragment extends Fragment {
    private AnyTopics anyTopics;
    private RecyclerView shopsRecyclerView;

    private ShopsAdapter adapter;

    private OnElementClick<AnyTopic> listener;

    public OnElementClick<AnyTopic> getListener() {
        return listener;
    }

    public void setListener(OnElementClick<AnyTopic> listener) {
        this.listener = listener;
    }

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

    public AnyTopics getAnyTopics() {
        return anyTopics;
    }


    private void updateUI() {


        adapter = new ShopsAdapter(anyTopics,getActivity());
        shopsRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<AnyTopic>() {
            @Override
            public void clickedOn(AnyTopic anyTopic, int position) {
                if (listener != null){
                    listener.clickedOn(anyTopic,position);
                }

            }
        });

    }

    public void setTopics(AnyTopics anyTopics) {
        this.anyTopics = anyTopics;
        updateUI();
    }

}
