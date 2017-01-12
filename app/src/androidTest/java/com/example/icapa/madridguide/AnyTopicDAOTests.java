package com.example.icapa.madridguide;


import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.icapa.madridguide.manager.db.AnyTopicDAO;
import com.example.icapa.madridguide.model.Activity;
import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.model.Shop;

import java.util.List;

import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_NAME;

public class AnyTopicDAOTests extends AndroidTestCase {


    public static final String SHOP_TESTING_NAME = "Shop testing name";
    public static final String SHOP_TESTING_ADD = "SHOP AD 1";

    public static final String ACTIVITY_TESTING_NAME = "Activity testing name";
    public static final String ACTIVITY_TESTING_ADD = "ACTIVITY AD 1";


    public void testCanInsertAShop(){
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());
        final int count = getCount(sut);

        final long id = insertTestShop(sut);

        assertTrue(id>0);
        assertTrue(count +1 == sut.queryCursor().getCount());

        final AnyTopic query = sut.query(id);
        assertEquals(query.getType(),Shop.TOPIC_NAME);
        // Se comprueba que es del tipo

    }

    public void testCanInsertAActivity(){
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());
        final int count = getCount(sut);
        final long id = insertTestActivity(sut);
        assertTrue(id>0);
        assertTrue(count+1 == sut.queryCursor().getCount());
        // Hasta aqui es que ha insertado, hay que ver el tipo
        final AnyTopic query = sut.query(id);
        assertEquals(query.getType(),Activity.TOPIC_NAME);
        // Se comprueba que es del tipo
    }

    private int getCount(AnyTopicDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

    private long insertTestShop(AnyTopicDAO sut) {
        final Shop shop = (Shop) new Shop(1, SHOP_TESTING_NAME).setAddress(SHOP_TESTING_ADD);
        shop.setLatitude(43.4614013);
        shop.setLongitude(-3.8462423);
        return sut.insert(shop);
    }


    private long insertTestActivity(AnyTopicDAO sut){
        final Activity activity = (Activity) new Activity(1,ACTIVITY_TESTING_NAME).setAddress(ACTIVITY_TESTING_ADD);
        return sut.insert(activity);
    }


    public void testQueryAllShops(){
        final AnyTopicDAO dao = new AnyTopicDAO(getContext());
        final long id_activity = insertTestActivity(dao);
        final long id_shop = insertTestShop(dao);

        List<AnyTopic> shops = dao.queryType(Shop.TOPIC_NAME);
        assertNotNull(shops);
        assertTrue(shops.size()>0);
        for (AnyTopic activity: shops){
            assertEquals(Shop.TOPIC_NAME,activity.getType());
        }

    }

    public void testQueryAllActivities(){
        final AnyTopicDAO dao = new AnyTopicDAO(getContext());
        final long id_activity = insertTestActivity(dao);
        final long id_shop = insertTestShop(dao);

        List<AnyTopic> activities = dao.queryType(Activity.TOPIC_NAME);
        assertNotNull(activities);
        assertTrue(activities.size()>0);
        for (AnyTopic activity: activities){
            assertEquals(Activity.TOPIC_NAME,activity.getType());
        }
    }

    public void testLatitudeLongitude(){
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());
        final int count = getCount(sut);

        final long id = insertTestShop(sut);

        AnyTopic myTopic = sut.query(id);
        assertTrue(id>0);
        assertTrue(myTopic.getLatitude()!=0.0);
        assertTrue(myTopic.getLongitude()!=0.0);
    }

    public void testQueryFilterActivity(){
        /*
        SELECT NAME,TYPE from TABLE_ANYTOPIC WHERE TYPE='Activity' AND NAME LIKE "%CANTABRIA%";
         */
        final String realString="CANTABRIA INFINITA";
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());
        Cursor c = sut.queryCursor("Activity","%CANTABRIA%");

        assertTrue(c.getCount()>0 && c.getCount()<3);

        String name = c.getString(c.getColumnIndex(KEY_ANYTOPIC_NAME));

        assertTrue(name.equals(realString)) ;


    }
}
