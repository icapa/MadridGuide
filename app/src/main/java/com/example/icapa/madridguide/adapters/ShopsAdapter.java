package com.example.icapa.madridguide.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;
import com.example.icapa.madridguide.views.ShopRowViewHolder;

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Shops mShops;

    public ShopsAdapter(Shops shops, Context context) {
        mShops = shops;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_shop,parent,false);
        return new ShopRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopRowViewHolder row, int position) {
        Shop shop = mShops.get(position);
        row.setShop(shop);

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return (int)mShops.size();
    }
}
