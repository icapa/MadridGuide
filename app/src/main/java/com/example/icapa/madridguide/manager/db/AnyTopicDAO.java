package com.example.icapa.madridguide.manager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.model.AnyTopics;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import static com.example.icapa.madridguide.manager.db.DBConstants.ALL_COLUMNS;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_ADDRESS;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_DESCRIPTION;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_ID;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_IMAGE_URL;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_LATITUDE;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_LOGO_IMAGE_URL;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_LONGITUDE;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_NAME;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_TYPE;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_ANYTOPIC_URL;
import static com.example.icapa.madridguide.manager.db.DBConstants.TABLE_ANYTOPIC;

public class AnyTopicDAO implements DAOPersistable<AnyTopic> {

    private WeakReference<Context> context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public AnyTopicDAO(Context context, DBHelper dbHelper ) {
        this.dbHelper = dbHelper;
        this.context = new WeakReference<>(context);
        this.db=dbHelper.getDB();
    }

    public AnyTopicDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }

    @Override
    public long insert(@NonNull AnyTopic anyTopic) {
        if ( anyTopic == null) {
            return 0;
        }
        // insert



        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_ANYTOPIC, null, this.getContentValues(anyTopic));
            anyTopic.setId(id);
            db.setTransactionSuccessful();  // COMMIT
        } finally {
            db.endTransaction();
        }


        return id;
    }

    public static @NonNull
    ContentValues getContentValues(final @NonNull AnyTopic anyTopic) {
        final ContentValues contentValues = new ContentValues();

        if (anyTopic == null){
            return null;
        }
        contentValues.put(KEY_ANYTOPIC_ADDRESS,anyTopic.getAddress());
        contentValues.put(KEY_ANYTOPIC_DESCRIPTION,anyTopic.getDescription());
        contentValues.put(KEY_ANYTOPIC_IMAGE_URL,anyTopic.getImageUrl());
        contentValues.put(KEY_ANYTOPIC_LOGO_IMAGE_URL,anyTopic.getLogoImgUrl());
        contentValues.put(KEY_ANYTOPIC_LATITUDE,anyTopic.getLatitude());
        contentValues.put(KEY_ANYTOPIC_LONGITUDE,anyTopic.getLongitude());
        contentValues.put(KEY_ANYTOPIC_NAME,anyTopic.getName());
        contentValues.put(KEY_ANYTOPIC_URL,anyTopic.getUrl());
        contentValues.put(KEY_ANYTOPIC_TYPE,anyTopic.getType());
        return contentValues;
    }
    public static @NonNull
    AnyTopic getShopFromContentValues(final @NonNull ContentValues contentValues){
        final AnyTopic anyTopic = new AnyTopic(1, "");

        //shop.setId(contentValues.getAsInteger(KEY_SHOP_ID));
        anyTopic.setName(contentValues.getAsString(KEY_ANYTOPIC_NAME));
        anyTopic.setAddress(contentValues.getAsString(KEY_ANYTOPIC_ADDRESS));
        anyTopic.setImageUrl(contentValues.getAsString(KEY_ANYTOPIC_IMAGE_URL));
        anyTopic.setLogoImgUrl(contentValues.getAsString(KEY_ANYTOPIC_LOGO_IMAGE_URL));
        anyTopic.setUrl(contentValues.getAsString(KEY_ANYTOPIC_URL));
        anyTopic.setLatitude(contentValues.getAsFloat(KEY_ANYTOPIC_LATITUDE));
        anyTopic.setLongitude(contentValues.getAsFloat(KEY_ANYTOPIC_LONGITUDE));
        anyTopic.setDescription(contentValues.getAsString(KEY_ANYTOPIC_DESCRIPTION));
        anyTopic.setType(contentValues.getAsString(KEY_ANYTOPIC_TYPE));
        return anyTopic;
    }


    @Override
    public void update(long id, @NonNull AnyTopic data) {

    }

    @Override
    public int delete(long id) {
        int num=-1;
        db.beginTransaction();
        try {
            num= db.delete(TABLE_ANYTOPIC, KEY_ANYTOPIC_ID + " = " + id, null); // 1s way
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
        //db.delete(TABLE_SHOP,  KEY_SHOP_ID + " = ?", new String[]{"" + id}); // 2s way
        return num;
    }

    @Override
    public void deleteAll() {
        db.beginTransaction();
        try {
            db.delete(TABLE_ANYTOPIC, null, null); // 1s way
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        Cursor c = db.query(TABLE_ANYTOPIC, ALL_COLUMNS, null, null,  null,null, KEY_ANYTOPIC_ID);
        if (c!=null && c.getCount()>0) {
            c.moveToFirst();
        }
        return c;
    }

    @Override
    public @Nullable AnyTopic query(final long id) {
        Cursor c = db.query(TABLE_ANYTOPIC, ALL_COLUMNS,  KEY_ANYTOPIC_ID + " = " + id, null, null, null, KEY_ANYTOPIC_ID);
        if (c!=null && c.getCount() ==1 ) {
            c.moveToFirst();

        }else{
            return null;
        }

        AnyTopic anyTopic = getAnyTopic(c);

        return anyTopic;
    }



    @Nullable
    @Override
    public List<AnyTopic> query() {
        Cursor c = this.queryCursor();

        if (c==null || !c.moveToFirst()){
            return null;
        }

        List<AnyTopic> anyTopics = new LinkedList<>();
        c.moveToFirst();
        do{
            anyTopics.add(getAnyTopic(c));
        } while(c.moveToNext());  // left golden path

        return anyTopics;
    }

    public List<AnyTopic> queryType(@NonNull final String type){
        Cursor c = this.queryCursor(type);
        if (c==null || !c.moveToFirst()){
            return null;
        }
        List<AnyTopic> anyTopics = new LinkedList<>();
        c.moveToFirst();
        do{
            anyTopics.add(getAnyTopic(c));

        }while(c.moveToNext());
        return anyTopics;
    }


    public static AnyTopic getAnyTopic(Cursor c) {
        long newId = c.getLong(c.getColumnIndex(KEY_ANYTOPIC_ID));
        String name = c.getString(c.getColumnIndex(KEY_ANYTOPIC_NAME));

        AnyTopic anyTopic = new AnyTopic(newId, name);

        anyTopic.setAddress(c.getString(c.getColumnIndex(KEY_ANYTOPIC_ADDRESS)));
        anyTopic.setDescription(c.getString(c.getColumnIndex(KEY_ANYTOPIC_DESCRIPTION)));
        anyTopic.setImageUrl(c.getString(c.getColumnIndex(KEY_ANYTOPIC_IMAGE_URL)));
        anyTopic.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_ANYTOPIC_LOGO_IMAGE_URL)));
        anyTopic.setLatitude(c.getFloat(c.getColumnIndex(KEY_ANYTOPIC_LATITUDE)));
        anyTopic.setLongitude(c.getFloat(c.getColumnIndex(KEY_ANYTOPIC_LONGITUDE)));
        anyTopic.setUrl(c.getString(c.getColumnIndex(KEY_ANYTOPIC_URL)));
        anyTopic.setType(c.getString(c.getColumnIndex(KEY_ANYTOPIC_TYPE)));
        return anyTopic;
    }

    @NonNull
    public static AnyTopics getAnyTopics(Cursor data) {
        List<AnyTopic> anyTopicList = new LinkedList<>();

        while(data.moveToNext()){
            AnyTopic anyTopic = AnyTopicDAO.getAnyTopic(data);
            anyTopicList.add(anyTopic);
        }
        return AnyTopics.build(anyTopicList);
    }

    public Cursor queryCursor(long id) {
        Cursor c = db.query(TABLE_ANYTOPIC, ALL_COLUMNS, "ID = " + id, null,  null,null, KEY_ANYTOPIC_ID);
        if (c!=null && c.getCount()>0) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor queryCursor(@NonNull final String type){
        Cursor c = db.query(TABLE_ANYTOPIC,ALL_COLUMNS,"TYPE = '"+type+"'",null,null,null,KEY_ANYTOPIC_ID);
        if (c!=null && c.getCount()>0){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor queryCursor(@NonNull final String type,@NonNull final String word){
        Cursor c = db.query(TABLE_ANYTOPIC,ALL_COLUMNS,"TYPE = ? AND NAME LIKE ? ",
                new String[]{type, "%"+ word + "%"},null,null,KEY_ANYTOPIC_ID);
        if (c!=null && c.getCount()>0){
            c.moveToFirst();
        }
        return c;
    }
}
