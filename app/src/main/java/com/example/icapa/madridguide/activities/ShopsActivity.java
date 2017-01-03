package com.example.icapa.madridguide.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.fragment.ShopsFragment;
import com.example.icapa.madridguide.interactors.GetAllShopsFromLocalCacheInteractor;
import com.example.icapa.madridguide.manager.db.DBConstants;
import com.example.icapa.madridguide.manager.db.ShopDAO;
import com.example.icapa.madridguide.manager.db.provider.MadridGuideProvider;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;
import com.example.icapa.madridguide.navigator.Navigator;
import com.example.icapa.madridguide.views.OnElementClick;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.util.List;


public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ShopsFragment mShopsFragment;
    private MapFragment mMapFragment;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        mShopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mGoogleMap = mMapFragment.getMap();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this,"No hay permisos para la localizacion",Toast.LENGTH_LONG)
                    .show();
        }else{
            if (mGoogleMap != null) {
                mGoogleMap.setMyLocationEnabled(true);
            }
        }

        GetAllShopsFromLocalCacheInteractor interactor = new GetAllShopsFromLocalCacheInteractor();
        interactor.execute(this, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCacheInteractor() {
            @Override
            public void completion(Shops shop) {
                mShopsFragment.setShops(shop);
            }
        });


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

        mShopsFragment.setListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(Shop shop, int position) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this,shop);
            }
        });
        mShopsFragment.setShops(shops);
    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
