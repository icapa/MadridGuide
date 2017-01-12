package com.example.icapa.madridguide.util;


import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsUtilities {

    private final static float zoomValue = 12.0f;

    private final static String BASE_URL_MAP="http://maps.googleapis.com/maps/api/staticmap?" +
            " center=%.6f,%.6f&zoom=15&size=320x220&scale=2&markers=%%7Ccolor:0x9" +
            " C7B14%%7C%.6f,%.6f";


    public static void CenterMap(@NonNull GoogleMap map,
                                 @NonNull final double latitude,
                                 @NonNull final double longitude){
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude,longitude)).zoom(zoomValue).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public static Marker PutAPin(@NonNull  GoogleMap map,
                                 @NonNull final MarkerOptions markerOptions){
        return map.addMarker(markerOptions);
    }



    public static MarkerOptions GetMarkerFromPosition(@NonNull final double latitude,
                                                      @NonNull final double longitude,
                                                      @NonNull final String title){
        MarkerOptions marker = new MarkerOptions().
                position(new LatLng(latitude,longitude)).
                title(title);
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        return marker;
    }

    public static List<Marker> PutAllPins(@NonNull final GoogleMap map,
                                          final List<MarkerOptions> markerOptions){
        List<Marker> markers = new ArrayList<>();
        for (MarkerOptions markerOption: markerOptions){
            markers.add(PutAPin(map,markerOption));
        }
        return markers;
    }

    public static String GetUrlImageFromMap(@NonNull final double latitude, @NonNull final double longitude){

        String urlString = String.format(Locale.ROOT,BASE_URL_MAP,latitude,longitude,latitude,longitude);
        return urlString;
    }

}
