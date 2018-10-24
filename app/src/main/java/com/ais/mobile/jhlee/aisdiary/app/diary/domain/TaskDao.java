package com.ais.mobile.jhlee.aisdiary.app.diary.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
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
public class TaskDao {

    public static final String TABLE_NAME = "tbTask";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_COMPLETED = "completed";
    public static final String COLUMN_CREATED = "created";
    public static final String COLUMN_UPDATED = "updated";

    static final String SQL_CREATE
            = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s NUMERIC DEFAULT 0, " +
                    "%s DATETIME DEFAULT (datetime('now')), " +
                    "%s DATETIME DEFAULT (datetime('now')));",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_CONTENT,
            COLUMN_COMPLETED,
            COLUMN_CREATED,
            COLUMN_UPDATED);

    static final String SQL_DROP = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

    private static final String SQL_READ
            = String.format("SELECT * FROM %s ORDER BY %s DESC;", TABLE_NAME, COLUMN_CREATED);

    //private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s = ?;", TABLE_NAME, COLUMN_ID);

    public long add(SQLiteDatabase database, Task task) {
        Date date = new Date();
        task.setCreated(Database.ISO8601.format(date));
        task.setUpdated(Database.ISO8601.format(date));

        return database.insert(TABLE_NAME, null, task.toContentValues(false));
    }

    public List<Task> getList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ, null);
        cursor.moveToFirst();

        List<Task> list = new ArrayList<>(cursor.getCount());

        try {
            do {
                Task item = new Task();
                item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                item.setContent(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)));
                item.setCompleted(cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETED)) == 1);
                item.setCreated(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED)));
                item.setUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED)));
                list.add(item);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return list;
    }

    public int update(SQLiteDatabase database, Task task) {
        if (task.getId() < 0) {
            return 0;
        }

        Date current = Calendar.getInstance().getTime();
        task.setUpdated(Database.ISO8601.format(current));

        ContentValues values = task.toContentValues();
        return database.update(TABLE_NAME, values, String.format("%s = ?", COLUMN_ID), new String[]{Long.toString(task.getId())});
    }

    public int deleteItemById(SQLiteDatabase database, long id) {
        return database.delete(TABLE_NAME, String.format("%s = ?", COLUMN_ID), new String[]{ Long.toString(id) });
    }

}
