package com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model;

import android.content.ContentValues;

import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.TransportDao;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 *
 * Type
 * Title
 * Address
 * Latitude
 * Longitude
 */
public class Transport {

    private long id = -1;
    private String type;
    private String title;
    private String address;
    private Double latitude;
    private Double longitude;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TransportDao.COLUMN_TYPE, type);
        values.put(TransportDao.COLUMN_TITLE, title);
        values.put(TransportDao.COLUMN_ADDRESS, address);
        values.put(TransportDao.COLUMN_LATITUDE, latitude);
        values.put(TransportDao.COLUMN_LONGITUDE, longitude);
        return values;
    }
}
