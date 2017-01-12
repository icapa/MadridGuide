package com.example.icapa.madridguide.views;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.activities.ShopsActivity;
import com.example.icapa.madridguide.model.AnyTopic;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class MapShopWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final ShopsActivity mActivity;
    private final View contentView;

    public MapShopWindowAdapter(@NonNull final ShopsActivity activity)
    {
        mActivity = activity;
        contentView = mActivity.getLayoutInflater().inflate(R.layout.map_shop_callout,null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        TextView name = (TextView) contentView.findViewById(R.id.map_shop_callout_name);
        ImageView logo = (ImageView) contentView.findViewById(R.id.map_shop_callout_logo);
        AnyTopic anyTopic = mActivity.getTopicFromMarker(marker);

        if (anyTopic == null){
            return null;
        }

        name.setText(anyTopic.getName());
        Picasso.with(mActivity)
                .load(anyTopic.getLogoImgUrl())
                .into(logo);

        return contentView;
    }



}

