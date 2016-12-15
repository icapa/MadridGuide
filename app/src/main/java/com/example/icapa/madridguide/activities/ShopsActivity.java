package com.example.icapa.madridguide.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.ShopsFragment;

public class ShopsActivity extends AppCompatActivity {

    private ShopsFragment mShopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        mShopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);

    }
}
