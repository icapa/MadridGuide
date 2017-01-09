package com.example.icapa.madridguide;

import android.test.AndroidTestCase;

import com.example.icapa.madridguide.model.Shop;


@SuppressWarnings("deprecation")
public class ShopTests extends AndroidTestCase {

    public static final String SHOP = "shop";
    public static final String ADDRESS = "ADDRESS";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String URL = "URL";

    public void testCanCreateAShop(){
        Shop sut = new Shop(0, SHOP);
        assertNotNull(sut);
    }

    public void testANewShopStoresDataCorrectly(){
        Shop sut = new Shop(10, SHOP);
        assertEquals(sut.getName(), SHOP);
        assertEquals(sut.getId(),10);
    }

    public void testANewShopStoresDataInPropertiesCorrectly(){
        Shop sut = (Shop) new Shop(11, SHOP)
                .setAddress(ADDRESS)
                .setDescription(DESCRIPTION)
                .setUrl(URL);
        assertEquals(sut.getAddress(), ADDRESS);
        assertEquals(sut.getDescription(), DESCRIPTION);
        assertEquals(sut.getUrl(), URL);
    }


}
