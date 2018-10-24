package com.ais.mobile.jhlee.aisdiary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class DateTimeManager {

    /**
     * Date date = ISO8601.parse("2018-10-18 12:39:50");
     * String datetime = ISO8601.format(date);
     */
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE dd MMM yyyy");
    public static SimpleDateFormat UPDATE_TIME_FORMAT = new SimpleDateFormat("HH:mm aa");
    public static SimpleDateFormat DURATION_TIME_FORMAT = new SimpleDateFormat("EEE dd MMM HH:mm aa");

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
