package com.example.icapa.madridguide;


import android.content.ContentResolver;
import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.icapa.madridguide.manager.db.DBConstants;
import com.example.icapa.madridguide.manager.db.provider.MadridGuideProvider;

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
        String filter ="CANTABRIA";
        ContentResolver cr = getContext().getContentResolver();




        Cursor c = cr.query(MadridGuideProvider.ACTIVITIES_URI,
                DBConstants.ALL_COLUMNS,
                filter, null,null);
        assertNotNull(c);
        assertTrue(c.getCount()>0);
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
