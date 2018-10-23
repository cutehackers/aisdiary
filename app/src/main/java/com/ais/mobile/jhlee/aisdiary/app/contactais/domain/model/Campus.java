package com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model;

import android.content.ContentValues;

import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.CampusDao;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 *
 * Name
 * Address
 * Phone
 * FAX
 * Latitude
 * Longitude
 */
public class Campus {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String fax;
    private Double latitude;
    private Double longitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
        values.put(CampusDao.COLUMN_NAME, name);
        values.put(CampusDao.COLUMN_ADDRESS, address);
        values.put(CampusDao.COLUMN_PHONE, phone);
        values.put(CampusDao.COLUMN_FAX, fax);
        values.put(CampusDao.COLUMN_LATITUDE, latitude);
        values.put(CampusDao.COLUMN_LONGITUDE, longitude);
        return values;
    }
}
