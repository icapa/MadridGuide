package com.example.icapa.madridguide.navigator;

import android.content.Intent;

import com.example.icapa.madridguide.activities.MainActivity;
import com.example.icapa.madridguide.activities.ShopDetailActivity;
import com.example.icapa.madridguide.activities.ShopsActivity;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.util.Constants;


public class Navigator {

    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;
    }
    public static Intent navigateFromShopsActivityToShopDetailActivity(final ShopsActivity shopsActivity,
                                                                       Shop shopDetail){
        final Intent i = new Intent(shopsActivity, ShopDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_SHOP_DETAIL,shopDetail);

        shopsActivity.startActivity(i);

        return i;
    }
}
