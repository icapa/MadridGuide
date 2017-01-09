package com.example.icapa.madridguide;

import android.test.AndroidTestCase;

import com.example.icapa.madridguide.model.Activity;


public class ActivityTests extends AndroidTestCase {
    public static final String ACTIVITY = "activity";
    public static final String ADDRESS = "ADDRESS";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String URL = "URL";

    public void testCanCreateActivity(){
        Activity sut = new Activity(0, ACTIVITY);
        assertNotNull(sut);
    }

    public void testANewActivityStoresDataCorrectly(){
        Activity sut = new Activity(10, ACTIVITY);
        assertEquals(sut.getName(), ACTIVITY);
        assertEquals(sut.getId(),10);
    }


    public void testANewActivityStoresDataInPropertiesCorrectly(){
        Activity sut = (Activity) new Activity(11, ACTIVITY)
                .setAddress(ADDRESS)
                .setDescription(DESCRIPTION)
                .setImageUrl(URL);
        assertEquals(sut.getAddress(), ADDRESS);
        assertEquals(sut.getDescription(), DESCRIPTION);
        assertEquals(sut.getImageUrl(), URL);
    }

    public void testActivityExtendsAnyTopic(){
        Activity sut = new Activity(10, ACTIVITY);
        assertEquals(sut.getClass().getSuperclass().getSimpleName(),
                "AnyTopic");
    }
}
