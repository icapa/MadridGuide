package com.example.icapa.madridguide;

import android.test.AndroidTestCase;


public class ShopDAOTests extends AndroidTestCase {

/*
    public static final String SHOP_TESTING_NAME = "Shop testing name";
    public static final String SHOP_TESTING_ADD = "AD 1";

    public void testCanInsertANewShop(){
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());
        final int count = getCount(sut);

        final long id = insertTestShop(sut);

        assertTrue(id>0);
        assertTrue(count +1 == sut.queryCursor().getCount());

    }

    public void testCanDeleteAShop(){
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());


        final long id = insertTestShop(sut);

        final int count = getCount(sut);



        assertEquals(1,sut.delete(id));

        assertTrue(count-1 == sut.queryCursor().getCount());
    }

    public void testDeleteAll(){
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());
        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0,count);

    }




    public void testQueryOneShop(){
        final ShopDAO dao = new ShopDAO(getContext());
        final long id = insertTestShop(dao);

        Shop sut = dao.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(),SHOP_TESTING_NAME);


    }


    public void testQueryAllShops(){
        final ShopDAO dao = new ShopDAO(getContext());

        final long id = insertTestShop(dao);

        List<Shop> shopLists = dao.query();
        assertNotNull(shopLists);

        assertTrue(shopLists.size()>0);
        for (Shop shop: shopLists){
            assertTrue(shop.getName().length()>0);
        }
    }

    public void testLatitudeLongitude(){
        final AnyTopicDAO sut = new AnyTopicDAO(getContext());
        final int count = getCount(sut);

        final long id = insertTestShop(sut);

        AnyTopic myTopic = sut.query(id);
        assertTrue(id>0);
        assertTrue(count +1 == sut.queryCursor().getCount());
        assertTrue(myTopic.getLatitude()>0.0);
        assertTrue(myTopic.getLongitude()>0.0);
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

*/

}
