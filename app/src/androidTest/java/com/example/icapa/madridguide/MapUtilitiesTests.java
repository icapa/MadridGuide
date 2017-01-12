package com.example.icapa.madridguide;


import android.test.AndroidTestCase;

import com.example.icapa.madridguide.util.MapsUtilities;

public class MapUtilitiesTests extends AndroidTestCase {
    public void testURLMapIsCorrect(){
        final String realURL =
                "http://maps.googleapis.com/maps/api/staticmap?" +
                        " center=1.000000,2.000000&zoom=17&size=320x220&scale=2&markers=%7Ccolor:0x9" +
                        " C7B14%7C1.000000,2.000000";
        double latitude = 1.0;
        double longitude= 2.0;
        String result = MapsUtilities.GetUrlImageFromMap(latitude,longitude);
        assertTrue(result.equals(realURL));

    }
}
