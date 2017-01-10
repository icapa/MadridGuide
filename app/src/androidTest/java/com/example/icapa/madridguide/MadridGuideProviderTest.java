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
