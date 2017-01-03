package com.example.icapa.madridguide;


import android.test.AndroidTestCase;

import com.example.icapa.madridguide.manager.preferences.ConstantsPreferences;
import com.example.icapa.madridguide.manager.preferences.InvalidateCacheCalendar;

import java.util.Date;

import static com.example.icapa.madridguide.manager.preferences.InvalidateCacheCalendar.CacheIsOld;

public class InvalidateCacheTests extends AndroidTestCase{

    public void testNotValueInPreferencesReturnsZero(){
        long value = InvalidateCacheCalendar.GetLastCacheData(getContext(), "lastCacheFake");
        assertEquals(value,0);
    }

    public void testValueIsStoreCorrectly(){
        long fakeDate = 123456;
        InvalidateCacheCalendar.SetLastCacheData(getContext(), ConstantsPreferences.lastCacheDate,fakeDate);
        long saveValue = 0;
        saveValue= InvalidateCacheCalendar.GetLastCacheData(getContext(),ConstantsPreferences.lastCacheDate);
        assertEquals(fakeDate,saveValue);
    }

    public void testCacheIsNotOld(){
        Date now = new Date();
        // Guardo la fecha de ahora,
        InvalidateCacheCalendar.SetLastCacheData(getContext(),ConstantsPreferences.lastCacheDate,now.getTime());

        boolean isOld = CacheIsOld(getContext(),now);

        assertEquals(isOld,false);



    }

    public void testCacheIsOld(){
        Date now = new Date();
        // Guardo la fecha de ahora,
        InvalidateCacheCalendar.SetLastCacheData(getContext(),ConstantsPreferences.lastCacheDate,now.getTime());
        // Sumo 7 dias
        now.setTime(now.getTime() + (7*24*3600*1000));

        boolean isOld = CacheIsOld(getContext(),now);

        assertEquals(isOld,true);
    }

    public void testCacheIsNotOldNear(){
        Date now = new Date();
        // Guardo la fecha de ahora,
        InvalidateCacheCalendar.SetLastCacheData(getContext(),ConstantsPreferences.lastCacheDate,now.getTime());
        now.setTime(now.getTime() + (6*24*3600*1000));
        boolean isOld = CacheIsOld(getContext(),now);

        assertEquals(isOld,false);

    }


}
