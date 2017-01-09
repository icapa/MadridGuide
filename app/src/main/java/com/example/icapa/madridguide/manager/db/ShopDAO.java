package com.example.icapa.madridguide.manager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import static com.example.icapa.madridguide.manager.db.DBConstants.ALL_COLUMNS;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_ADDRESS;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_DESCRIPTION;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_ID;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_IMAGE_URL;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_LATITUDE;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_LOGO_IMAGE_URL;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_LONGITUDE;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_NAME;
import static com.example.icapa.madridguide.manager.db.DBConstants.KEY_SHOP_URL;
import static com.example.icapa.madridguide.manager.db.DBConstants.TABLE_SHOP;

public class ShopDAO implements DAOPersistable<Shop>{

    private WeakReference<Context> context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public ShopDAO(Context context, DBHelper dbHelper ) {
        this.dbHelper = dbHelper;
        this.context = new WeakReference<>(context);
        this.db=dbHelper.getDB();
    }

    public ShopDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }


@Override
    public long insert(@NonNull Shop shop) {
        if (shop == null) {
            return 0;
        }
        // insert



        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_SHOP, null, this.getContentValues(shop));
            shop.setId(id);
            db.setTransactionSuccessful();  // COMMIT
        } finally {
            db.endTransaction();
        }


        return id;

    }

    public static @NonNull
    ContentValues getContentValues(final @NonNull Shop shop) {
        final ContentValues contentValues = new ContentValues();

        if (shop == null){
            return null;
        }
        contentValues.put(KEY_SHOP_ADDRESS,shop.getAddress());
        contentValues.put(KEY_SHOP_DESCRIPTION,shop.getDescription());
        contentValues.put(KEY_SHOP_IMAGE_URL,shop.getImageUrl());
        contentValues.put(KEY_SHOP_LOGO_IMAGE_URL,shop.getLogoImgUrl());
        contentValues.put(KEY_SHOP_LATITUDE,shop.getLatitude());
        contentValues.put(KEY_SHOP_LONGITUDE,shop.getLongitude());
        contentValues.put(KEY_SHOP_NAME,shop.getName());
        contentValues.put(KEY_SHOP_URL,shop.getUrl());

        return contentValues;
    }
    public static @NonNull Shop getShopFromContentValues(final @NonNull ContentValues contentValues){
        final Shop shop = new Shop(1,"");

        //shop.setId(contentValues.getAsInteger(KEY_SHOP_ID));
        shop.setName(contentValues.getAsString(KEY_SHOP_NAME));
        shop.setAddress(contentValues.getAsString(KEY_SHOP_ADDRESS));
        shop.setImageUrl(contentValues.getAsString(KEY_SHOP_IMAGE_URL));
        shop.setLogoImgUrl(contentValues.getAsString(KEY_SHOP_LOGO_IMAGE_URL));
        shop.setUrl(contentValues.getAsString(KEY_SHOP_URL));
        shop.setLatitude(contentValues.getAsFloat(KEY_SHOP_LATITUDE));
        shop.setLongitude(contentValues.getAsFloat(KEY_SHOP_LONGITUDE));
        shop.setDescription(contentValues.getAsString(KEY_SHOP_DESCRIPTION));


        return shop;
    }
    @Override
    public void update(final long id, @NonNull Shop data) {


    }

    @Override
    public int delete(final long id) {
        int num=-1;
        db.beginTransaction();
        try {
            num= db.delete(TABLE_SHOP, KEY_SHOP_ID + " = " + id, null); // 1s way
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
            db.delete(TABLE_SHOP, null, null); // 1s way
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, null, null,  null,null, KEY_SHOP_ID);
        if (c!=null && c.getCount()>0) {
            c.moveToFirst();
        }
        return c;
    }

    @Override
    public @Nullable Shop query(final long id) {
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS,  KEY_SHOP_ID + " = " + id, null, null, null, KEY_SHOP_ID);
        if (c!=null && c.getCount() ==1 ) {
            c.moveToFirst();

        }else{
            return null;
        }

        Shop shop = getShop(c);

        return shop;
    }

    @NonNull
    public static Shop getShop(Cursor c) {
        long newId = c.getLong(c.getColumnIndex(KEY_SHOP_ID));
        String name = c.getString(c.getColumnIndex(KEY_SHOP_NAME));

        Shop shop = new Shop(newId, name);

        shop.setAddress(c.getString(c.getColumnIndex(KEY_SHOP_ADDRESS)));
        shop.setDescription(c.getString(c.getColumnIndex(KEY_SHOP_DESCRIPTION)));
        shop.setImageUrl(c.getString(c.getColumnIndex(KEY_SHOP_IMAGE_URL)));
        shop.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_SHOP_LOGO_IMAGE_URL)));
        shop.setLatitude(c.getFloat(c.getColumnIndex(KEY_SHOP_LATITUDE)));
        shop.setLongitude(c.getFloat(c.getColumnIndex(KEY_SHOP_LONGITUDE)));
        shop.setUrl(c.getString(c.getColumnIndex(KEY_SHOP_URL)));
        return shop;
    }

    @NonNull
    public static Shops getShops(Cursor data) {
        List<Shop> shopList = new LinkedList<>();

        while(data.moveToNext()){
            Shop shop = ShopDAO.getShop(data);
            shopList.add(shop);
        }

        return Shops.build(shopList);
    }

    @Nullable
    @Override
    public List<Shop> query() {
        Cursor c = this.queryCursor();

        if (c==null || !c.moveToFirst()){
            return null;
        }

        List<Shop> shops = new LinkedList<>();
        c.moveToFirst();
        do{
            shops.add(getShop(c));
        } while(c.moveToNext());  // left golden path

        return shops;
    }

    public Cursor queryCursor(long id) {
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, "ID = " + id, null,  null,null, KEY_SHOP_ID);
        if (c!=null && c.getCount()>0) {
            c.moveToFirst();
        }
        return c;
    }
}
