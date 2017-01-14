package com.example.icapa.madridguide.activities;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.fragment.ShopsFragment;
import com.example.icapa.madridguide.manager.db.AnyTopicDAO;
import com.example.icapa.madridguide.manager.db.DBConstants;
import com.example.icapa.madridguide.manager.db.provider.MadridGuideProvider;
import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.model.AnyTopics;
import com.example.icapa.madridguide.navigator.Navigator;
import com.example.icapa.madridguide.util.MapsUtilities;
import com.example.icapa.madridguide.views.MapShopWindowAdapter;
import com.example.icapa.madridguide.views.OnElementClick;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.icapa.madridguide.util.Constants.latitudeMadrid;
import static com.example.icapa.madridguide.util.Constants.longitudeMadrid;


public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnInfoWindowClickListener {

    public static final String TOPIC_TO_SHOW = "TOPIC_TO_SHOW";
    public static final int SHOW_SHOPS = 1;
    public static final int SHOW_ACTIVITIES=2;

    private int whatToShow = SHOW_SHOPS;

    private ShopsFragment mShopsFragment;
    private MapFragment mMapFragment;
    private GoogleMap mGoogleMap;

    private List<MarkerOptions> markersOptions = null;
    private List<Marker> markers = null;

    private String filterName=null;

    private LoaderManager loaderManager;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        filterName = getIntentFilterName(intent);
        loaderManager.restartLoader(0,null,this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shops_activity_menu,menu);
        configureSearchViewInActionBar(menu);
        return true;

    }

    private void configureSearchViewInActionBar(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    filterName=null;
                    loaderManager.restartLoader(0,null,ShopsActivity.this);
                }
                return false;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_shops);

        whatToShow = GetWhatToShow();

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
                mGoogleMap.setInfoWindowAdapter(new
                        MapShopWindowAdapter(this));
                MapsUtilities.CenterMap(mGoogleMap, latitudeMadrid, longitudeMadrid);
                mGoogleMap.setOnInfoWindowClickListener(this);
            }
        }
        Intent intent = getIntent();
        filterName = getIntentFilterName(intent);

        loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0,null,this);

    }

    private String getIntentFilterName(Intent intent) {
        String query=null;
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
        }
        return query;
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri contentProviderUri;

        if (whatToShow == ShopsActivity.SHOW_SHOPS){
            contentProviderUri = MadridGuideProvider.SHOPS_URI;
        }else{
            contentProviderUri = MadridGuideProvider.ACTIVITIES_URI;
        }

        mGoogleMap.clear();

        CursorLoader loader = new CursorLoader(this,
                contentProviderUri,  // Uri
                DBConstants.ALL_COLUMNS,        //projections
                filterName,                           // where
                null,                           // campos del where
                null                            // order
                );

        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        AnyTopics anyTopics = AnyTopicDAO.getAnyTopics(data);

        mShopsFragment.setListener(new OnElementClick<AnyTopic>() {
            @Override
            public void clickedOn(AnyTopic anyTopic, int position) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this,anyTopic);
            }
        });
        mShopsFragment.setTopics(anyTopics);
        markersOptions = createMarkersFromTopics(anyTopics);
        markers = MapsUtilities.PutAllPins(mGoogleMap,markersOptions);
    }


    private List<MarkerOptions> createMarkersFromTopics(@NonNull final AnyTopics anyTopics) {
        List<MarkerOptions> markerOptions = new ArrayList<>();
        for (AnyTopic topic: anyTopics.allAnyTopics()){
            markerOptions.add(MapsUtilities.GetMarkerFromPosition(
                    topic.getLatitude(),topic.getLongitude(),topic.getName()));
        }
        return markerOptions;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    private int GetWhatToShow(){
        Intent intent = getIntent();
        int wts = intent.getIntExtra(ShopsActivity.TOPIC_TO_SHOW,SHOW_SHOPS);
        return wts;
    }


    public AnyTopic getTopicFromMarker(@NonNull final Marker marker){
        int position;
        position = markers.indexOf(marker);
        if (position>=0) {
            AnyTopic anyTopic = mShopsFragment.getAnyTopics().allAnyTopics()
                    .get(position);
            return anyTopic;
        }
        else{
            return null;
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        AnyTopic topic = getTopicFromMarker(marker);
        if (topic == null){
            return;
        }
        Navigator.navigateFromShopsActivityToShopDetailActivity(this,topic);
    }
}
