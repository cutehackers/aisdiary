package com.ais.mobile.jhlee.aisdiary.app.diary.domain.model;

import android.content.ContentValues;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.TaskDao;

/**
 * Create: 23/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Task {

    private long id = -1;
    private String content;
    private Boolean completed = false;
    private String created;
    private String updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public ContentValues toContentValues() {
        return toContentValues(true);
    }

    public ContentValues toContentValues(boolean withId) {
        ContentValues values = new ContentValues();
        values.put(TaskDao.COLUMN_CONTENT, content);
        values.put(TaskDao.COLUMN_COMPLETED, completed);
        values.put(TaskDao.COLUMN_CREATED, created);
        values.put(TaskDao.COLUMN_UPDATED, updated);

        if (withId) values.put(TaskDao.COLUMN_ID, id);
        return values;
    }
}
