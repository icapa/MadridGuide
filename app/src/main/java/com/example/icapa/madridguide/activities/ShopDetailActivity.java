package com.example.icapa.madridguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.util.Constants;
import com.example.icapa.madridguide.util.MapsUtilities;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_shop_detail_shop_name_text)
    TextView shopNameText;

    @BindView(R.id.activity_shop_detail_shop_logo_image)
    ImageView shopLogoImage;

    @BindView(R.id.activity_shop_detail_description)
    TextView shopDescription;

    @BindView(R.id.activity_shop_detail_address)
    TextView shopAddress;

    @BindView(R.id.activity_shop_detail_map)
    ImageView shopMap;



    AnyTopic mAnyTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);


        getDetailShopFromCallingIntent();

        updateUI();

    }

    private void getDetailShopFromCallingIntent() {
        Intent i = getIntent();
        if (i != null){
            mAnyTopic = (AnyTopic) i.getSerializableExtra(Constants.INTENT_KEY_SHOP_DETAIL);
        }
    }

    private void updateUI() {
        shopAddress.setText(mAnyTopic.getAddress());
        shopDescription.setText(mAnyTopic.getDescription());
        shopNameText.setText(mAnyTopic.getName());
        Picasso.with(this)
                .load(mAnyTopic.getLogoImgUrl())
                .into(shopLogoImage);
        Picasso.with(this)
                .load(MapsUtilities.GetUrlImageFromMap(mAnyTopic.getLatitude(),mAnyTopic.getLongitude()))
                .into(shopMap);


    }
}
