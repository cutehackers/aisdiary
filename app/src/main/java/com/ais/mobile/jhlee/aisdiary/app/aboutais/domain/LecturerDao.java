package com.ais.mobile.jhlee.aisdiary.app.aboutais.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class LecturerDao {

    public static final String TABLE_NAME = "tbLecturer";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";                // personal, academic, workshop
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_MAIL = "mail";
    public static final String COLUMN_QUALIFICATION = "qualifications";
    public static final String COLUMN_MAIN_TEACHING_FIELD = "main_teaching_field";

    public static final int SORT_COLUMN_INDEX = 1; // name

    static final String SQL_CREATE
            = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT);",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_PHONE,
            COLUMN_MAIL,
            COLUMN_QUALIFICATION,
            COLUMN_MAIN_TEACHING_FIELD);

    static final String SQL_DROP = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

    private static final String SQL_READ
            = String.format("SELECT * FROM %s ORDER BY %s;", TABLE_NAME, COLUMN_NAME);

    private static final String SQL_READ_BY_NAME
            = String.format("SELECT * FROM %s WHERE %s LIKE ? ORDER BY %s;", TABLE_NAME, COLUMN_NAME, COLUMN_NAME);

//    private static final String SQL_READ_BY_TYPE
//            = String.format("SELECT * FROM %s WHERE %s = ? ORDER BY %s DESC;", TABLE_NAME, COLUMN_NAME, COLUMN_NAME);


    public long getCount(SQLiteDatabase database) {
        return DatabaseUtils.queryNumEntries(database, TABLE_NAME);
    }

    public long add(SQLiteDatabase database, Lecturer lecturer) {
        return database.insert(TABLE_NAME, null, lecturer.toContentValues(false));
    }

    public List<Lecturer> getList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ, null);
        cursor.moveToFirst();

        List<Lecturer> lecturers = new ArrayList<>(cursor.getCount());

        try {
            do {
                Lecturer item = new Lecturer();
                item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                item.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                item.setMail(cursor.getString(cursor.getColumnIndex(COLUMN_MAIL)));
                item.setQualifications(cursor.getString(cursor.getColumnIndex(COLUMN_QUALIFICATION)));
                item.setMainTeachingField(cursor.getString(cursor.getColumnIndex(COLUMN_MAIN_TEACHING_FIELD)));
                lecturers.add(item);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return lecturers;
    }

    public List<Lecturer> getListByName(SQLiteDatabase database, String searchQuery) {
//        Cursor cursor = database.rawQuery(SQL_READ_BY_NAME, new String[]{String.format("'%%s%'", searchQuery)});

        String filter = searchQuery == null ? "" : searchQuery;
        Cursor cursor = database.query(TABLE_NAME, null,
                COLUMN_NAME + " LIKE '%"+filter+"%'", null,null, null, COLUMN_NAME,null);
//        mDb.query(true, DATABASE_NAMES_TABLE, new String[] { KEY_ROWID,
//                        KEY_NAME }, KEY_NAME + " LIKE ?",
//                new String[] { filter+"%" }, null, null, null,
//                null);

        cursor.moveToFirst();

        List<Lecturer> lecturers = new ArrayList<>(cursor.getCount());

        try {
            do {
                Lecturer item = new Lecturer();
                item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                item.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                item.setMail(cursor.getString(cursor.getColumnIndex(COLUMN_MAIL)));
                item.setQualifications(cursor.getString(cursor.getColumnIndex(COLUMN_QUALIFICATION)));
                item.setMainTeachingField(cursor.getString(cursor.getColumnIndex(COLUMN_MAIN_TEACHING_FIELD)));
                lecturers.add(item);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return lecturers;
    }

    public int update(SQLiteDatabase database, Lecturer lecturer) {
        if (lecturer.getId() < 0) {
            return 0;
        }

        ContentValues values = lecturer.toContentValues();
        return database.update(TABLE_NAME, values, String.format("%s = ?", COLUMN_ID), new String[]{Long.toString(lecturer.getId())});
    }

    public int delete(SQLiteDatabase database, Lecturer lecturer) {
        return deleteItemById(database, lecturer.getId());
    }

    public int deleteItemById(SQLiteDatabase database, long id) {
        if (id < 0) {
            return 0;
        }

        return database.delete(TABLE_NAME, String.format("%s = ?", COLUMN_ID), new String[]{ Long.toString(id) });
    }
}
