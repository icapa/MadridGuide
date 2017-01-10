package com.example.icapa.madridguide.manager.db.provider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.icapa.madridguide.manager.db.AnyTopicDAO;
import com.example.icapa.madridguide.model.Activity;
import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.model.Shop;

public class MadridGuideProvider extends ContentProvider {
    public static final String MADRIDGUIDE_PROVIDER = "com.example.icapa.madridguide.provider";
    public static final Uri SHOPS_URI = Uri.parse("content://" + MADRIDGUIDE_PROVIDER + "/shops");

    public static final Uri ACTIVITIES_URI = Uri.parse("content://"+MADRIDGUIDE_PROVIDER+"/activities");

    // Create the constants used to differentiate between the different URI requests.
    private static final int ALL_SHOPS = 1;
    private static final int SINGLE_SHOP = 2;
    private static final int ALL_ACTIVITIES = 3;
    private static final int SINGLE_ACTIVITY = 4;

    private static final UriMatcher uriMatcher;
    // Populate the UriMatcher object, where a URI ending in ‘elements’ will correspond to a request for all items, and ‘elements/[rowID]’ represents a single row.
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops", ALL_SHOPS);
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops/#", SINGLE_SHOP);
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "activities",ALL_ACTIVITIES);
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "activities/#",SINGLE_ACTIVITY);

    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        AnyTopicDAO dao = new AnyTopicDAO(getContext());

        Cursor cursor= null;
        String rowID;

        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP :
                rowID = uri.getPathSegments().get(1);
                cursor = dao.queryCursor(Long.parseLong(rowID));
                break;
            case ALL_SHOPS:
                cursor = dao.queryCursor(Shop.TOPIC_NAME);
                break;
            case SINGLE_ACTIVITY:
                rowID = uri.getPathSegments().get(1);
                cursor = dao.queryCursor(Long.parseLong(rowID));
                break;
            case ALL_ACTIVITIES:
                cursor = dao.queryCursor(Activity.TOPIC_NAME);
                break;
            default: break;
        }


        cursor.setNotificationUri(getContext().getContentResolver(), uri);


        return cursor;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String type = null;
        switch (uriMatcher.match(uri)){
            case SINGLE_SHOP:
                type = "vnd.android.cursor.item/vnd.com.example.icapa.madridguide.provider";
                break;
            case ALL_SHOPS:
                type = "vnd.android.cursor.dir/vnd.com.example.icapa.madridguide.provider";
                break;
            case ALL_ACTIVITIES:
                type = "vnd.android.cursor.dirAct/vnd.com.example.icapa.madridguide.provider";
                break;
            case SINGLE_ACTIVITY:
                type = "vnd.android.cursor.itemAct/vnd.com.example.icapa.madridguide.provider";
                break;
            default:
                break;
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        AnyTopicDAO dao = new AnyTopicDAO(getContext());


        AnyTopic anyTopic = AnyTopicDAO.getShopFromContentValues(contentValues);

        long id = dao.insert(anyTopic);

        // Construct and return the URI of the newly inserted row.
        if (id == -1) {
            return null;
        }
        // Construct and return the URI of the newly inserted row.
        Uri insertedUri = null;
        switch (uriMatcher.match(uri)) {
            case ALL_SHOPS:
                insertedUri = ContentUris.withAppendedId(SHOPS_URI, id);
                break;
            case ALL_ACTIVITIES:
                insertedUri = ContentUris.withAppendedId(ACTIVITIES_URI,id);
                break;
            default:
                break;
        }

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(insertedUri, null);

        return insertedUri;


    }

    @Override
    public int delete(Uri uri, String where, String[] whereSelection) {

        AnyTopicDAO dao = new AnyTopicDAO(getContext());


        int deleteCount=0;
        String rowID;

        // If this is a row URI, limit the deletion to the specified row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                rowID = uri.getPathSegments().get(1);
                deleteCount=dao.delete(Long.parseLong(rowID));
                break;
            case SINGLE_ACTIVITY:
                rowID = uri.getPathSegments().get(1);
                deleteCount=dao.delete(Long.parseLong(rowID));
                break;
            case ALL_SHOPS:
                dao.deleteAll();
                break;
            case ALL_ACTIVITIES:    // Borro todo tambien
                dao.deleteAll();
                break;
            default:
                break;
        }


        getContext().getContentResolver().notifyChange(uri, null);

        // Return the number of deleted items.

        return deleteCount;

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
