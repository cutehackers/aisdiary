package com.ais.mobile.jhlee.aisdiary.app.aboutais.domain;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Programme;

import java.util.ArrayList;
import java.util.List;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class ProgrammeDao {

    public static final String TABLE_NAME = "tbProgramme";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DEPARTMENT = "department";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TITLE = "title";

    public static final String MAP_TABLE_NAME = "tbProgrammeLecturer";

    static final String SQL_CREATE
            = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL, " +
                    "%s TEXT NOT NULL);",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_DEPARTMENT,
            COLUMN_CODE,
            COLUMN_TITLE);

    /**
     * TODO make some mapping table for managing course and course coordinator some day later
     * [programme id, lecturer id]
     */
//    static final String SQL_CREATE_LECTURER_MAP
//            = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s INTEGER NOT NULL);");

    static final String SQL_DROP = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

    private static final String SQL_READ_DEPARTMENTS
            = String.format("SELECT DISTINCT %s FROM %s;", COLUMN_DEPARTMENT, TABLE_NAME);

    private static final String SQL_READ
            = String.format("SELECT * FROM %s ORDER BY %s;", TABLE_NAME, COLUMN_CODE);


    public long getCount(SQLiteDatabase database) {
        return DatabaseUtils.queryNumEntries(database, TABLE_NAME);
    }

    public long add(SQLiteDatabase database, Programme programme) {
        return database.insert(TABLE_NAME, null, programme.toContentValues(false));
    }

    public List<String> getDepartmentList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ_DEPARTMENTS, null);
        cursor.moveToFirst();

        List<String> departments = new ArrayList<>(cursor.getCount());

        try {
            do {
                departments.add(cursor.getString(0));
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return departments;
    }

    public List<Programme> getList(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(SQL_READ, null);
        cursor.moveToFirst();

        List<Programme> programmes = new ArrayList<>(cursor.getCount());

        try {
            do {
                Programme item = new Programme();
                item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                item.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)));
                item.setCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                programmes.add(item);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return programmes;
    }

    public void addSampleModels(SQLiteDatabase database) {
        add(database, newProgramme("7.101", "The Information Technology System"));
        add(database, newProgramme("7.102", "Business Communication"));
        add(database, newProgramme("7.103", "Fundamentals of Computer Programming"));
        add(database, newProgramme("7.104", "Database Engineering 1"));
        add(database, newProgramme("7.105", "Fundamentals of Computer Networking"));
        add(database, newProgramme("7.201", "Systems Analysis and Design"));
        add(database, newProgramme("7.203", "Computer Algorithms and Discrete Mathematics"));
        add(database, newProgramme("7.205", "Object Oriented Programming"));
        add(database, newProgramme("7.206", "Desktop Applications Development"));
        add(database, newProgramme("7.214", "Database Engineering 2"));
        add(database, newProgramme("7.217", "Requirement Modelling"));
        add(database, newProgramme("7.218", "Server Administration"));
        add(database, newProgramme("7.301", "Information Technology Project Management"));
        add(database, newProgramme("7.308", "Mobile Application Development"));
        add(database, newProgramme("7.309", "Network System Security"));
        add(database, newProgramme("7.311", "Mobile Network Design"));
        add(database, newProgramme("7.314", "E-Business Strategies"));
        add(database, newProgramme("7.320", "Information Technology Project"));
        add(database, newProgramme("7.321", "Intensive Information Technology Project"));
        add(database, newProgramme("7.401", "Research Methods"));
        add(database, newProgramme("7.403", "Internship"));
        add(database, newProgramme("7.405", "Specialisation Project"));
        add(database, newProgramme("7.408", "Software User Experience"));
        add(database, newProgramme("7.410", "Computer and Communication Network Security"));
        add(database, newProgramme("7.414", "Enterprise Cloud-base Systems"));
    }

    private Programme newProgramme(String code, String title) {
        Programme programme = new Programme();
        programme.setDepartment("Information Technology");
        programme.setCode(code);
        programme.setTitle(title);
        return programme;
    }
}
