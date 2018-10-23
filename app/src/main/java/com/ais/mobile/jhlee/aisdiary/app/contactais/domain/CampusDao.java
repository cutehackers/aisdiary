package com.ais.mobile.jhlee.aisdiary.app.contactais.domain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Campus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class CampusDao {

    public static final String TABLE_NAME = "tbCampus";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_FAX = "fax";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    static final String SQL_CREATE
            = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL NOT NULL, %s REAL NOT NULL);",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_ADDRESS,
            COLUMN_PHONE,
            COLUMN_FAX,
            COLUMN_LATITUDE,
            COLUMN_LONGITUDE);

    static final String SQL_DROP = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

    private static final String SQL_READ = String.format("SELECT * FROM %s;", TABLE_NAME);


    CampusDao() { }

    public long add(SQLiteDatabase database, Campus campus) {
        return database.insert(TABLE_NAME, null, campus.toContentValues());
    }

    public List<Campus> getList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ, null);
        cursor.moveToFirst();

        List<Campus> campuses = new ArrayList<>(cursor.getCount());

        try {
            do {
                Campus campus = new Campus();
                campus.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                campus.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                campus.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                campus.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                campus.setFax(cursor.getString(cursor.getColumnIndex(COLUMN_FAX)));
                campus.setLatitude(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUDE)));
                campus.setLongitude(cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUDE)));
                campuses.add(campus);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return campuses;
    }
}
