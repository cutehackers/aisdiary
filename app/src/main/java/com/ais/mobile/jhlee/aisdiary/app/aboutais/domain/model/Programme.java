package com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.ProgrammeDao;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Programme implements Parcelable {

    private long id;
    private String department;
    private String code;
    private String title;

    public Programme() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() { return String.format("%s %s", code, title); }

    public ContentValues toContentValues() {
        return toContentValues(true);
    }

    public ContentValues toContentValues(boolean withId) {
        ContentValues values = new ContentValues();
        values.put(ProgrammeDao.COLUMN_DEPARTMENT, department);
        values.put(ProgrammeDao.COLUMN_CODE, code);
        values.put(ProgrammeDao.COLUMN_TITLE, title);

        if (withId) values.put(ProgrammeDao.COLUMN_ID, id);
        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(department);
        dest.writeString(code);
        dest.writeString(title);
    }

    public Programme(Parcel in) {
        id = in.readLong();
        department = in.readString();
        code = in.readString();
        title = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Programme createFromParcel(Parcel in) {
            return new Programme(in);
        }

        public Programme[] newArray(int size) {
            return new Programme[size];
        }
    };
}
