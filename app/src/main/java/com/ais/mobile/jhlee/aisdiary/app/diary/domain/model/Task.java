package com.ais.mobile.jhlee.aisdiary.app.diary.domain.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.TaskDao;
import com.ais.mobile.jhlee.aisdiary.base.Database;

import java.text.ParseException;
import java.util.Date;

/**
 * Create: 23/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Task implements Parcelable {

    private long id = -1;
    private String content;
    private Boolean completed = false;
    private String created;
    private String updated;

    public Task() { }

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

    public Date getUpdatedAsDate() throws ParseException {
        return Database.ISO8601.parse(updated);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(content);
        dest.writeByte((byte)(completed ? 1 : 0));
        dest.writeString(created);
        dest.writeString(updated);
    }

    public Task(Parcel in) {
        id = in.readLong();
        content = in.readString();
        completed = in.readByte() != 0;
        created = in.readString();
        updated = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
