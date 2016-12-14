package com.example.icapa.madridguide.navigator;

import android.content.Intent;

import com.example.icapa.madridguide.activities.MainActivity;
import com.example.icapa.madridguide.activities.ShopsActivity;


public class Navigator {

    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;
    }
}
