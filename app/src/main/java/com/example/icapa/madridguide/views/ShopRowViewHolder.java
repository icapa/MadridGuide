package com.example.icapa.madridguide.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.model.Shop;


public class ShopRowViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private ImageView logoImageView;

    public ShopRowViewHolder(View rowShop) {
        super(rowShop);
        nameTextView = (TextView) rowShop.findViewById(R.id.row_shop_name);
        logoImageView = (ImageView) rowShop.findViewById(R.id.row_shop_logo);
    }

    public void setShop(final @NonNull  Shop shop){
        if (shop == null) {
            return;
        }
        nameTextView.setText(shop.getName());
        logoImageView.setImageBitmap(null);
    }
}
