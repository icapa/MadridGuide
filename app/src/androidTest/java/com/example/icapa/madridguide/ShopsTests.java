package com.example.icapa.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("deprecation")
public class ShopsTests extends AndroidTestCase {
    public void testCreatingAShopsWithNullListsReturnsNonNullShops(){
        Shops sut = Shops.build(null);
        assertNotNull(sut);
        assertNotNull(sut.allShops());
    }

    public void testCreatingAShopsWithNAListReturnsNonNullShops(){
        List<Shop> data = getShops();

        Shops sut = Shops.build(data);
        assertNotNull(sut);
        assertNotNull(sut.allShops());
        assertEquals(sut.allShops(),data);
        assertEquals(sut.allShops().size(),data.size());

    }

    @NonNull
    private List<Shop> getShops() {
        List<Shop> data = new ArrayList<>();
        data.add(new Shop(1,"1").setAddress("AD 1"));
        data.add(new Shop(1,"2").setAddress("AD 2"));
        return data;
    }
}
