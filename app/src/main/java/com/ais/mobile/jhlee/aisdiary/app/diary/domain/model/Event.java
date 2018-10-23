package com.ais.mobile.jhlee.aisdiary.app.diary.domain.model;

import android.content.ContentValues;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.EventDao;
import com.ais.mobile.jhlee.aisdiary.base.Database;

import java.text.ParseException;
import java.util.Date;

/**
 * Create: 23/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Event {

    private long id = -1;
    private String type;
    private String title;
    private String startTime;
    private String endTime;
    private String location;
    private String description;
    private String created;
    private String updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getStartTimeAsDate() throws ParseException {
        return Database.ISO8601.parse(startTime);
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getEndTimeAsDate() throws ParseException {
        return Database.ISO8601.parse(endTime);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        values.put(EventDao.COLUMN_TYPE, type);
        values.put(EventDao.COLUMN_TITLE, title);
        values.put(EventDao.COLUMN_START_TIME, startTime);
        values.put(EventDao.COLUMN_END_TIME, endTime);
        values.put(EventDao.COLUMN_LOCATION, location);
        values.put(EventDao.COLUMN_DESCRIPTION, description);
        values.put(EventDao.COLUMN_CREATED, created);
        values.put(EventDao.COLUMN_UPDATED, updated);

        if (withId) values.put(EventDao.COLUMN_ID, id);
        return values;
    }
}
