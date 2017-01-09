package com.example.icapa.madridguide.manager.db;


public class DBConstants {



    public static final String TABLE_SHOP = "SHOP";

    // Table field constants
    public static final String KEY_SHOP_ID = "_id";
    public static final String KEY_SHOP_NAME = "NAME";
    public static final String KEY_SHOP_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String KEY_SHOP_ADDRESS = "ADDRESS";
    public static final String KEY_SHOP_URL = "URL";
    public static final String KEY_SHOP_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_SHOP_LATITUDE = "LATITUDE";
    public static final String KEY_SHOP_LONGITUDE = "LONGITUDE";

/*
    public static final String[] ALL_COLUMNS = {
        KEY_SHOP_ID,
        KEY_SHOP_NAME,
        KEY_SHOP_IMAGE_URL,
        KEY_SHOP_LOGO_IMAGE_URL,
        KEY_SHOP_ADDRESS,
        KEY_SHOP_URL,
        KEY_SHOP_DESCRIPTION,
        KEY_SHOP_LATITUDE,
        KEY_SHOP_LONGITUDE
    };
*/







    public static final String SQL_SCRIPT_CREATE_SHOP_TABLE =
            "create table "
                    + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_DESCRIPTION + " text "
                    + ");";
    /*
    public static final String DROP_DATABASE_SCRIPTS = "";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_SHOP_TABLE
    };

    */





    public static final String TABLE_ANYTOPIC = "TABLE_ANYTOPIC";

    // Table field constants
    public static final String KEY_ANYTOPIC_ID = "_id";
    public static final String KEY_ANYTOPIC_NAME = "NAME";
    public static final String KEY_ANYTOPIC_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_ANYTOPIC_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String KEY_ANYTOPIC_ADDRESS = "ADDRESS";
    public static final String KEY_ANYTOPIC_URL = "URL";
    public static final String KEY_ANYTOPIC_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_ANYTOPIC_LATITUDE = "LATITUDE";
    public static final String KEY_ANYTOPIC_LONGITUDE = "LONGITUDE";
    public static final String KEY_ANYTOPIC_TYPE = "TYPE";


    public static final String[] ALL_COLUMNS = {
            KEY_ANYTOPIC_ID,
            KEY_ANYTOPIC_NAME,
            KEY_ANYTOPIC_IMAGE_URL,
            KEY_ANYTOPIC_LOGO_IMAGE_URL,
            KEY_ANYTOPIC_ADDRESS,
            KEY_ANYTOPIC_URL,
            KEY_ANYTOPIC_DESCRIPTION,
            KEY_ANYTOPIC_LATITUDE,
            KEY_ANYTOPIC_LONGITUDE,
            KEY_ANYTOPIC_TYPE
    };








    public static final String SQL_SCRIPT_CREATE_ANYTOPIC_TABLE =
            "create table "
                    + TABLE_ANYTOPIC
                    + "( "
                    + KEY_ANYTOPIC_ID + " integer primary key autoincrement, "
                    + KEY_ANYTOPIC_NAME + " text not null,"
                    + KEY_ANYTOPIC_IMAGE_URL + " text, "
                    + KEY_ANYTOPIC_LOGO_IMAGE_URL + " text, "
                    + KEY_ANYTOPIC_ADDRESS + " text,"
                    + KEY_ANYTOPIC_URL + " text,"
                    + KEY_ANYTOPIC_LATITUDE + " real,"
                    + KEY_ANYTOPIC_LONGITUDE + " real, "
                    + KEY_ANYTOPIC_DESCRIPTION + " text, "
                    + KEY_ANYTOPIC_TYPE + " text"
                    + ");";

    public static final String DROP_DATABASE_SCRIPTS = "";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_ANYTOPIC_TABLE
    };


}
