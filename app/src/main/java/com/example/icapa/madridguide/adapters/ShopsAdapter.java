package com.example.icapa.madridguide.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.model.AnyTopics;
import com.example.icapa.madridguide.views.OnElementClick;
import com.example.icapa.madridguide.views.ShopRowViewHolder;

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder> {
    private final LayoutInflater layoutInflater;
    private AnyTopics anyTopics = null;


    private OnElementClick<AnyTopic> listener;



    public ShopsAdapter(final AnyTopics anyTopics, Context context) {
        this.anyTopics = anyTopics;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_shop,parent,false);
        return new ShopRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopRowViewHolder row, final int position) {
        final AnyTopic anyTopic = anyTopics.get(position);
        row.setAnyTopic(anyTopic);

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopsAdapter.this.listener != null){
                    listener.clickedOn(anyTopic,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (int)anyTopics.size();
    }

    public void setOnElementClickListener(@NonNull final OnElementClick listener){
        this.listener = listener;
    }
}
