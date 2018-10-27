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
            = String.format("SELECT * FROM %s WHERE %s = '"+TYPE_PERSONAL+"' ORDER BY %s DESC;", TABLE_NAME, COLUMN_TYPE, COLUMN_START_TIME);

    private static final String SQL_READ_SCHEDULE
            = String.format("SELECT * FROM %s WHERE %s = '"+TYPE_ACADEMIC+"' OR %s = '"+TYPE_WORKSHOP+"' ORDER BY %s DESC;",
            TABLE_NAME,
            COLUMN_TYPE,
            COLUMN_TYPE,
            COLUMN_START_TIME);

    //private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s = ?;", TABLE_NAME, COLUMN_ID);

    public long getCount(SQLiteDatabase database) {
        return DatabaseUtils.queryNumEntries(database, TABLE_NAME);
    }

    public long getScheduleCount(SQLiteDatabase database) {
        return DatabaseUtils.queryNumEntries(database, TABLE_NAME,
                String.format("%s = '"+TYPE_ACADEMIC+"' OR %s = '"+TYPE_WORKSHOP+"'", COLUMN_TYPE, COLUMN_TYPE), null);
    }

    public long add(SQLiteDatabase database, Event event) {
        Date date = new Date();
        event.setCreated(Database.ISO8601.format(date));
        event.setUpdated(Database.ISO8601.format(date));

        return database.insert(TABLE_NAME, null, event.toContentValues(false));
    }

    public List<Event> getList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ_BY_TYPE, null);
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

    public List<Event> getScheduleList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ_SCHEDULE, null);
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

    /**
     * add schedule sample models
     */
    public void addSampleModels(SQLiteDatabase database) {
        Event event1 = new Event();
        event1.setType(TYPE_ACADEMIC);
        event1.setTitle("New Students Orientation Day");
        event1.setLocation("Asquith Campus");
        event1.setStartTime("2018-09-06 09:00:00");
        event1.setEndTime("2018-09-06 16:00:00");


        Event event2 = new Event();
        event2.setType(TYPE_ACADEMIC);
        event2.setTitle("Graduation Day");
        event2.setLocation("Asquith Campus");
        event2.setStartTime("2018-09-14 09:00:00");
        event2.setEndTime("2018-09-14 16:00:00");

        Event event3 = new Event();
        event3.setType(TYPE_ACADEMIC);
        event3.setTitle("MBA New Student's Orientation Day");
        event3.setLocation("Asquith Campus");
        event3.setStartTime("2018-11-01 09:00:00");
        event3.setEndTime("2018-11-01 16:00:00");

        Event event4 = new Event();
        event4.setType(TYPE_ACADEMIC);
        event4.setTitle("EXAM week");
        event4.setLocation("Asquith Campus");
        event4.setStartTime("2018-12-02 09:00:00");
        event4.setEndTime("2018-12-07 17:00:00");

        Event event5 = new Event();
        event5.setType(TYPE_ACADEMIC);
        event5.setTitle("END semester 3");
        event5.setLocation("Asquith Campus");
        event5.setStartTime("2018-12-14 09:00:00");
        event5.setEndTime("2018-12-14 17:00:00");

        Event event6 = new Event();
        event6.setType(TYPE_WORKSHOP);
        event6.setTitle("Academic Writing and Assignment Skills");
        event6.setLocation("St Helens Campus R107/R108");
        event6.setStartTime("2018-09-17 12:30:00");
        event6.setEndTime("2018-09-17 14:30:00");

        Event event7 = new Event();
        event7.setType(TYPE_WORKSHOP);
        event7.setTitle("APA Referencing Skills");
        event7.setLocation("St Helens Campus R107/R108");
        event7.setStartTime("2018-09-20 12:30:00");
        event7.setEndTime("2018-09-20 14:30:00");

        add(database, event1);
        add(database, event2);
        add(database, event3);
        add(database, event4);
        add(database, event5);
        add(database, event6);
        add(database, event7);
    }

}
