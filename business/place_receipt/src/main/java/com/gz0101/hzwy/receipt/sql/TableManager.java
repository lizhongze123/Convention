package com.gz0101.hzwy.receipt.sql;

import android.database.sqlite.SQLiteDatabase;

public final class TableManager {
    public static final String TABLE_NAME = "address";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String TOWN = "town";
    public static final String STREET = "street";
    public static final String AREA = "area";
    public static final String DEFAULT = "isDefault";
    private TableManager() {
        throw new IllegalStateException();
    }

     static void createAddress(SQLiteDatabase db) {
        if (db == null) {
            return;
        }
        String sql = "create table " + TABLE_NAME + "(" +
                ID + " integer primary key autoincrement," +
                NAME + " text," +
                PHONE + " text," +
                PROVINCE + " text," +
                CITY + " text," +
                TOWN + " text," +
                STREET + " text," +
                AREA + " text," +
                DEFAULT + " integer)";
        db.execSQL(sql);
     }
}
