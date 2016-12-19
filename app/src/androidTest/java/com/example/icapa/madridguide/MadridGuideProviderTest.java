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
}
