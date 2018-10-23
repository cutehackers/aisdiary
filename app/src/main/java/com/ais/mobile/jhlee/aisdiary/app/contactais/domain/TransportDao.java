package com.ais.mobile.jhlee.aisdiary.app.contactais.domain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Transport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TransportDao {

    public static final String TABLE_NAME = "tbTransport";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    static final String SQL_CREATE
            = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT NOT NULL, %s TEXT, %s TEXT, %s REAL NOT NULL, %s REAL NOT NULL);",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_TYPE,
            COLUMN_TITLE,
            COLUMN_ADDRESS,
            COLUMN_LATITUDE,
            COLUMN_LONGITUDE);

    static final String SQL_DROP = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

    private static final String SQL_READ = String.format("SELECT * FROM %s;", TABLE_NAME);


    TransportDao() { }

    public long add(SQLiteDatabase database, Transport transport) {
        return database.insert(TABLE_NAME, null, transport.toContentValues());
    }

    public List<Transport> getList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ, null);
        cursor.moveToFirst();

        List<Transport> transports = new ArrayList<>(cursor.getCount());

        try {
            do {
                Transport transport = new Transport();
                transport.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                transport.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
                transport.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                transport.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                transport.setLatitude(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUDE)));
                transport.setLongitude(cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUDE)));
                transports.add(transport);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return transports;
    }
}
