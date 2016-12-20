package com.example.icapa.madridguide.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.fragment.ShopsFragment;
import com.example.icapa.madridguide.manager.db.DBConstants;
import com.example.icapa.madridguide.manager.db.ShopDAO;
import com.example.icapa.madridguide.manager.db.provider.MadridGuideProvider;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;

import java.util.List;


public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ShopsFragment mShopsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        mShopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);


        //getShops();
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0,null,this);

    }

    // 1st attempt at async cursor load: works!
    public void getShops() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(ShopsActivity.this);

                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                ShopsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mShopsFragment.setShops(shops);
                    }
                });

            }
        }).start();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this,
                MadridGuideProvider.SHOPS_URI,  // Uri
                DBConstants.ALL_COLUMNS,        //projections
                null,                           // where
                null,                           // campos del where
                null                            // order
                );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        final Shops shops = ShopDAO.getShops(data);
        mShopsFragment.setShops(shops);
    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
