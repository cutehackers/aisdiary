package com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.LecturerDao;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Lecturer implements Parcelable {

    private long id = -1;
    private String name;
    private String phone;
    private String mail;
    private String qualifications;
    private String mainTeachingField;

    public Lecturer() { }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(mail);
        dest.writeString(qualifications);
        dest.writeString(mainTeachingField);
    }

    public Lecturer(Parcel in) {
        id = in.readLong();
        name = in.readString();
        phone = in.readString();
        mail = in.readString();
        qualifications = in.readString();
        mainTeachingField = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Lecturer createFromParcel(Parcel in) {
            return new Lecturer(in);
        }

        public Lecturer[] newArray(int size) {
            return new Lecturer[size];
        }
    };
}
