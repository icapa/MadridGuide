package com.example.icapa.madridguide;


import android.content.ContentResolver;
import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.icapa.madridguide.manager.db.AnyTopicDAO;
import com.example.icapa.madridguide.manager.db.DBConstants;
import com.example.icapa.madridguide.manager.db.provider.MadridGuideProvider;
import com.example.icapa.madridguide.model.AnyTopics;

@SuppressWarnings("deprecation")
public class MadridGuideProviderTest extends AndroidTestCase {

    public void testQueryAllShops(){
        ContentResolver cr = getContext().getContentResolver();
        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS,null,null,null);

        assertNotNull(c);

    }

    public void testQueryAllActivities(){
        ContentResolver cr = getContext().getContentResolver();
        Cursor c = cr.query(MadridGuideProvider.ACTIVITIES_URI,DBConstants.ALL_COLUMNS,null,null,null);
        assertNotNull(c);
    }

    public void testQueryFilterActivities(){
        AnyTopicDAO dao = new AnyTopicDAO(getContext());
        Cursor cAll = dao.queryCursor();
        /* This test is just if the database
        filled based on the data got from the
         */
        if (cAll.getCount()== 0){
            return;
        }

        //-- Test real activity
        final String filter ="tour";
        ContentResolver cr = getContext().getContentResolver();
        Cursor c = cr.query(MadridGuideProvider.ACTIVITIES_URI,
                DBConstants.ALL_COLUMNS,
                filter, null,null);
        int num = c.getCount();
        assertNotNull(c);
        assertEquals(2,num);

        //-- Test fake name activity
        final String fakeFilter = "fake";
        Cursor c2 = cr.query(MadridGuideProvider.ACTIVITIES_URI,
                DBConstants.ALL_COLUMNS,
                fakeFilter, null,null);
        int numFake = c2.getCount();
        assertNotNull(c2);
        assertEquals(0,numFake);

        //-- Test real activity
        final String otherFilter = "Prado";
        Cursor c3 = cr.query(MadridGuideProvider.ACTIVITIES_URI,
                DBConstants.ALL_COLUMNS,
                otherFilter, null,null);
        int numPrado = c3.getCount();
        assertNotNull(c3);
        assertEquals(1,numPrado);

    }

    public void testNumberTotalActivities(){
        /* This test is just if data is inside the bbdd */
        AnyTopicDAO dao = new AnyTopicDAO(getContext());
        Cursor cAll = dao.queryCursor();
        /* This test is just if the database
        filled based on the data got from the
         */

        if (cAll.getCount()== 0){
            return;
        }

        ContentResolver cr = getContext().getContentResolver();
        Cursor c = cr.query(MadridGuideProvider.ACTIVITIES_URI,
                DBConstants.ALL_COLUMNS,
                null,null,null
                );
        int numberOfActivities = c.getCount();
        assertEquals(14,numberOfActivities);

        AnyTopics anyTopics = AnyTopicDAO.getAnyTopics(c);
        assertEquals(14,anyTopics.size());


    }
/*
    public void testFilterActivities(){

        AnyTopicDAO dao = new AnyTopicDAO(getContext());


        final String sa1 = "%CANTABRIA%";
        Activity activity = new Activity(99,"CANTABRIA INFINITA");

        long result = dao.insert(activity);

        if (result > 0) {
            ContentResolver cr = getContext().getContentResolver();
            Cursor c = cr.query(
                    MadridGuideProvider.ACTIVITIES_URI,
                    DBConstants.ALL_COLUMNS,
                    DBConstants.KEY_ANYTOPIC_NAME + " LIKE ?",
                    new String[]{sa1},
                    null);
            // Lo borro
            assertEquals(c.getCount(),1);
            dao.delete(result);
        }


    }
    */
    /*
    public void testInsertAShop(){
        ContentResolver cr = getContext().getContentResolver();
        Shop shop = new Shop(1,"Little shop of horrors!");

        final Cursor beforeCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS,null,null,null);
        final int beforeCount = beforeCursor.getCount();


        Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedUri);

        final Cursor afterCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS,null,null,null);
        final int afterCount = afterCursor.getCount();

        assertEquals(beforeCount+1,afterCount);

    }
    */
    /*
    public void testDeleteAShop(){
        ContentResolver cr = getContext().getContentResolver();
        Shop shop = new Shop(1,"Test delete shop");


        final Cursor beforeCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS,null,null,null);
        final int beforeCount = beforeCursor.getCount();

        Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedUri);
    }
    */
}
