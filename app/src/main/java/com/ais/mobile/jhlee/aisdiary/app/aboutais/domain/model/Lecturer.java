package com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model;

import android.content.ContentValues;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.LecturerDao;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Lecturer {

    private long id = -1;
    private String name;
    private String phone;
    private String mail;
    private String qualifications;
    private String mainTeachingField;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getMainTeachingField() {
        return mainTeachingField;
    }

    public void setMainTeachingField(String mainTeachingField) {
        this.mainTeachingField = mainTeachingField;
    }

    public ContentValues toContentValues() {
        return toContentValues(true);
    }

    public ContentValues toContentValues(boolean withId) {
        ContentValues values = new ContentValues();
        values.put(LecturerDao.COLUMN_NAME, name);
        values.put(LecturerDao.COLUMN_PHONE, phone);
        values.put(LecturerDao.COLUMN_MAIL, mail);
        values.put(LecturerDao.COLUMN_QUALIFICATION, qualifications);
        values.put(LecturerDao.COLUMN_MAIN_TEACHING_FIELD, mainTeachingField);

        if (withId) values.put(LecturerDao.COLUMN_ID, id);
        return values;
    }
}
