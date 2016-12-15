package com.example.icapa.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.icapa.madridguide.manager.db.ShopDAO;
import com.example.icapa.madridguide.model.Shop;


public class ShopDAOTests extends AndroidTestCase {
    public void testCanInsertANewShop(){
        final ShopDAO sut = new ShopDAO(getContext());
        final Cursor cursor = sut.queryCursor();
        final int count = cursor.getCount();
        final Shop shop = new Shop(1, "1").setAddress("AD 1");

        final long id = sut.insert(shop);
        assertTrue(id>0);
        assertTrue(count +1 == sut.queryCursor().getCount());


    }

}
