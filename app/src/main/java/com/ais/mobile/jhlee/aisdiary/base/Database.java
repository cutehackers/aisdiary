package com.ais.mobile.jhlee.aisdiary.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.ContactAisDataSource;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "aisdiary.db";
    private static final int DATABASE_VERSION = 1;

    private static Database INSTANCE = null;

    Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ContactAisDataSource.instance().createTables(db);
        ContactAisDataSource.instance().addSampleModels(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ContactAisDataSource.instance().dropTables(db);
        onCreate(db);
    }

    public static Database instance() {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                INSTANCE = new Database(AndroidContext.instance().getApplication());
            }
        }
        return INSTANCE;
    }
}
