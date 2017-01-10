package com.example.icapa.madridguide.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.model.AnyTopic;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;


public class ShopRowViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private ImageView logoImageView;
    private WeakReference<Context> context;
    public ShopRowViewHolder(View rowShop) {
        super(rowShop);
        context = new WeakReference<Context>(rowShop.getContext());

        nameTextView = (TextView) rowShop.findViewById(R.id.row_shop_name);
        logoImageView = (ImageView) rowShop.findViewById(R.id.row_shop_logo);
    }

    public void setAnyTopic(final @NonNull AnyTopic anyTopic){
        if (anyTopic == null) {
            return;
        }
        nameTextView.setText(anyTopic.getName());
        Picasso.with(context.get())
                .load(anyTopic.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);
    }
}
