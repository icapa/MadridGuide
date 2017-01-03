package com.example.icapa.madridguide.manager.preferences;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.icapa.madridguide.util.Constants;

import java.util.Date;

public class InvalidateCacheCalendar {

    private static final int DAYS_FOR_INVALIDATE_CACHE = 7;

    public static long GetLastCacheData(final Context context, final String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.appName,Context.MODE_PRIVATE);
        long date = sharedPreferences.getLong(key,0);
        return date;
    }

    public static long SetLastCacheData(final Context context,final String key, final long date){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.appName,Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key,date).apply();
        return date;
    }

    public static boolean CacheIsOld(final Context context, final Date dateNow){

        long msNow = dateNow.getTime();
        long msLastUpdate = GetLastCacheData(context, ConstantsPreferences.lastCacheDate);

        float days = (float)((msNow-msLastUpdate)  / (1000*3600*24));
        if (days >= DAYS_FOR_INVALIDATE_CACHE) {
            return true;
        }
        else {
            return false;
        }

    }
    public static long MarkCacheAsValid(final Context context){
        long resp=0;
        resp = SetLastCacheData(context,ConstantsPreferences.lastCacheDate,new Date().getTime());
        return resp;

    }


}
