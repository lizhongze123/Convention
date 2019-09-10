package com.gz0101.hzwy.receipt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gz0101.hzwy.baselibrary.app.ContextCacheHelper;
import com.gz0101.hzwy.receipt.bean.PlaceItem;
import com.gz0101.hzwy.receipt.sql.DBHelper;
import com.gz0101.hzwy.receipt.sql.TableManager;

import java.util.ArrayList;
import java.util.List;

public final class AddressHelper {
    private static final int VERSION = 1;

    private DBHelper mHelper;
    private SQLiteDatabase mDataBase;

    private AddressHelper() {
        Context context = ContextCacheHelper.getInstance().getContext();
        if (context == null) {
            return;
        }
        mHelper = new DBHelper(context, VERSION);
        mDataBase = mHelper.getWritableDatabase();
    }

    private static class Holder{
        private static final AddressHelper SELF = new AddressHelper();
    }

    public static AddressHelper getInstance() {
        return Holder.SELF;
    }

    public boolean addAddress(PlaceItem item) {
        if (item == null || mDataBase == null) {
            return false;
        }
        boolean isDefault = item.isDefault();
        ContentValues values = new ContentValues();
        values.put(TableManager.NAME, item.getName());
        values.put(TableManager.PHONE, item.getPhone());
        values.put(TableManager.PROVINCE, item.getProvince());
        values.put(TableManager.CITY, item.getCity());
        values.put(TableManager.TOWN, item.getTown());
        values.put(TableManager.STREET, item.getStreet());
        values.put(TableManager.AREA, item.getArea());
        values.put(TableManager.DEFAULT, isDefault?1:0);
        if (isDefault) {
            mDataBase.execSQL("update " + TableManager.TABLE_NAME + " set "
                    + TableManager.DEFAULT + " = 0");
        }
        long id = mDataBase.insert(TableManager.TABLE_NAME, null, values);
        item.setId(id);
        return true;
    }

    public boolean updateAddress(PlaceItem item) {
        if (item == null || mDataBase == null) {
            return false;
        }
        boolean isDefault = item.isDefault();
        ContentValues values = new ContentValues();
        values.put(TableManager.NAME, item.getName());
        values.put(TableManager.PHONE, item.getPhone());
        values.put(TableManager.PROVINCE, item.getProvince());
        values.put(TableManager.CITY, item.getCity());
        values.put(TableManager.TOWN, item.getTown());
        values.put(TableManager.STREET, item.getStreet());
        values.put(TableManager.AREA, item.getArea());
        values.put(TableManager.DEFAULT, isDefault?1:0);
        if (isDefault) {
            mDataBase.execSQL("update " + TableManager.TABLE_NAME + " set "
                    + TableManager.DEFAULT + " = 0");
        }
        String[] whereArgs = {String.valueOf(item.getId())};
        mDataBase.update(TableManager.TABLE_NAME, values, "id=?", whereArgs);
        return true;
    }

    public PlaceItem getDefaultAddress() {
        if (mDataBase == null) {
            return null;
        }
        String sql = "select * from "+ TableManager.TABLE_NAME + " where " + TableManager.DEFAULT + " = 1";
        Cursor cursor ;
        try {
            cursor = mDataBase.rawQuery(sql, null);
            if (cursor.getCount() == 0) {
                return null;
            }
            PlaceItem item = new PlaceItem();
            while (cursor.moveToNext()) {
                item.setId(cursor.getLong(cursor.getColumnIndex(TableManager.ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(TableManager.NAME)));
                item.setPhone(cursor.getString(cursor.getColumnIndex(TableManager.PHONE)));
                item.setProvince(cursor.getString(cursor.getColumnIndex(TableManager.PROVINCE)));
                item.setCity(cursor.getString(cursor.getColumnIndex(TableManager.CITY)));
                item.setTown(cursor.getString(cursor.getColumnIndex(TableManager.TOWN)));
                item.setStreet(cursor.getString(cursor.getColumnIndex(TableManager.STREET)));
                item.setArea(cursor.getString(cursor.getColumnIndex(TableManager.AREA)));
                int value = cursor.getInt(cursor.getColumnIndex(TableManager.DEFAULT));
                item.setDefault(value == 1);
            }
            cursor.close();
            return item;
        }catch (Exception e) {
            return null;
        }
    }

    public  List<PlaceItem> geAllAddress() {
        if (mDataBase == null) {
            return null;
        }
        String sql = "select * from "+ TableManager.TABLE_NAME;
        Cursor cursor ;
        try {
            cursor = mDataBase.rawQuery(sql, null);
            if (cursor.getCount() == 0) {
                return null;
            }
            List<PlaceItem> list = new ArrayList<>();
            while (cursor.moveToNext()) {
                PlaceItem item = new PlaceItem();
                item.setId(cursor.getLong(cursor.getColumnIndex(TableManager.ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(TableManager.NAME)));
                item.setPhone(cursor.getString(cursor.getColumnIndex(TableManager.PHONE)));
                item.setProvince(cursor.getString(cursor.getColumnIndex(TableManager.PROVINCE)));
                item.setCity(cursor.getString(cursor.getColumnIndex(TableManager.CITY)));
                item.setTown(cursor.getString(cursor.getColumnIndex(TableManager.TOWN)));
                item.setStreet(cursor.getString(cursor.getColumnIndex(TableManager.STREET)));
                item.setArea(cursor.getString(cursor.getColumnIndex(TableManager.AREA)));
                int value = cursor.getInt(cursor.getColumnIndex(TableManager.DEFAULT));
                item.setDefault(value == 1);
                list.add(item);
            }
            cursor.close();
            return list;
        }catch (Exception e) {
            return null;
        }
    }

    public void deleteAddress(long id) {
        String sql = "delete  from "+ TableManager.TABLE_NAME + " where id = " + id;
        mDataBase.execSQL(sql);
    }

    public boolean isHasAddress(PlaceItem item) {
        if (item == null || mDataBase == null) {
            return false;
        }
        try {
            String sql = "select * from " + TableManager.TABLE_NAME + " where id = " + item.getId() + ";";
            Cursor cursor = mDataBase.rawQuery(sql, null);
            int count = cursor.getCount();
            cursor.close();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
