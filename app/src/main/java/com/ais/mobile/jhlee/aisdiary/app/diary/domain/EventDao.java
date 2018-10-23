package com.ais.mobile.jhlee.aisdiary.app.diary.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.base.Database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Create: 23/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EventDao {

    public static final String TABLE_NAME = "tbEvent";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";                // personal, academic, workshop
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CREATED = "created";
    public static final String COLUMN_UPDATED = "updated";

    public static final String TYPE_PERSONAL = "personal";
    public static final String TYPE_ACADEMIC = "academic";
    public static final String TYPE_WORKSHOP = "workshop";

    static final String SQL_CREATE
            = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s DATETIME DEFAULT (datetime('now')), " +
                    "%s DATETIME DEFAULT (datetime('now')));",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_TYPE,
            COLUMN_TITLE,
            COLUMN_START_TIME,
            COLUMN_END_TIME,
            COLUMN_LOCATION,
            COLUMN_DESCRIPTION,
            COLUMN_CREATED,
            COLUMN_UPDATED);

    static final String SQL_DROP = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

    private static final String SQL_READ_BY_TYPE
            = String.format("SELECT * FROM %s WHERE %s = ? ORDER BY %s DESC;", TABLE_NAME, COLUMN_TYPE, COLUMN_START_TIME);

    //private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s = ?;", TABLE_NAME, COLUMN_ID);

    public long getCount(SQLiteDatabase database) {
        return DatabaseUtils.queryNumEntries(database, TABLE_NAME);
    }
    public long add(SQLiteDatabase database, Event event) {
        Date date = new Date();
        event.setCreated(Database.ISO8601.format(date));
        event.setUpdated(Database.ISO8601.format(date));

        return database.insert(TABLE_NAME, null, event.toContentValues(false));
    }

    public List<Event> getListByType(SQLiteDatabase database, String type) {
        Cursor cursor = database.rawQuery(SQL_READ_BY_TYPE, new String[]{ type });
        cursor.moveToFirst();

        List<Event> events = new ArrayList<>(cursor.getCount());

        try {
            do {
                Event item = new Event();
                item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                item.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                item.setStartTime(cursor.getString(cursor.getColumnIndex(COLUMN_START_TIME)));
                item.setEndTime(cursor.getString(cursor.getColumnIndex(COLUMN_END_TIME)));
                item.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
                item.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                item.setCreated(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED)));
                item.setUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED)));
                events.add(item);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return events;
    }

    public int update(SQLiteDatabase database, Event event) {
        if (event.getId() < 0) {
            return 0;
        }

        Date current = Calendar.getInstance().getTime();
        event.setUpdated(Database.ISO8601.format(current));

        ContentValues values = event.toContentValues();
        return database.update(TABLE_NAME, values, String.format("%s = ?", COLUMN_ID), new String[]{Long.toString(event.getId())});
    }

    public int deleteItemById(SQLiteDatabase database, long id) {
        if (id < 0) {
            return 0;
        }

        return database.delete(TABLE_NAME, String.format("%s = ?", COLUMN_ID), new String[]{ Long.toString(id) });
    }
}
