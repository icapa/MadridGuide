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

}
