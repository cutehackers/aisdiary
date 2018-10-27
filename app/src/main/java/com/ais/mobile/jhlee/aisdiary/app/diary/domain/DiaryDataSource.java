package com.ais.mobile.jhlee.aisdiary.app.diary.domain;

import android.database.sqlite.SQLiteDatabase;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
import com.ais.mobile.jhlee.aisdiary.base.Database;

import java.util.List;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class DiaryDataSource {

    private static DiaryDataSource INSTANCE;

    private EventDao eventDao = new EventDao();
    private TaskDao taskDao = new TaskDao();

    private DiaryDataSource() { }

    public static DiaryDataSource instance() {
        if (INSTANCE == null) {
            synchronized (DiaryDataSource.class) {
                INSTANCE = new DiaryDataSource();
            }
        }
        return INSTANCE;
    }

    public final void createTables(SQLiteDatabase database) {
        database.execSQL(EventDao.SQL_CREATE);
        database.execSQL(TaskDao.SQL_CREATE);
    }

    public final void dropTables(SQLiteDatabase database) {
        database.execSQL(EventDao.SQL_DROP);
        database.execSQL(TaskDao.SQL_DROP);
    }

    public long getEventCount() {
        return eventDao.getCount(Database.instance().getReadableDatabase());
    }

    public List<Event> getEventList() {
        return eventDao.getList(Database.instance().getReadableDatabase());
    }

    public long add(Event event) {
        return eventDao.add(Database.instance().getWritableDatabase(), event);
    }

    public int update(Event event) {
        return eventDao.update(Database.instance().getWritableDatabase(), event);
    }

    public int delete(Event event) {
        return eventDao.deleteItemById(Database.instance().getWritableDatabase(), event.getId());
    }

    public List<Task> getTaskList() {
        return taskDao.getList(Database.instance().getReadableDatabase());
    }

    public long add(Task task) {
        return taskDao.add(Database.instance().getWritableDatabase(), task);
    }

    public int update(Task task) {
        return taskDao.update(Database.instance().getWritableDatabase(), task);
    }

    public int delete(Task task) {
        return taskDao.deleteItemById(Database.instance().getWritableDatabase(), task.getId());
    }

    public List<Event> getScheduleList() {
        SQLiteDatabase database = Database.instance().getWritableDatabase();

        ensureScheduleModels(database);
        return eventDao.getScheduleList(database);
    }

    private void ensureScheduleModels(SQLiteDatabase database) {
        if (eventDao.getScheduleCount(database) < 1) {
            eventDao.addSampleModels(database);
        }
    }
}
