package com.openclassrooms.mymeeting.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    private static final TimeZone timeZone = TimeZone.getDefault();
    @SuppressLint("SimpleDateFormat")
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");

    public static String getStringFromDate(Date date) {

        return formatter.format(date);
    }

    /**
     * @param string date pattern: "dd/MM/yy HH:mm"
     * @return Date
     */
    public static Date getDateFromString(String string) {
        Date date;
        try {
            formatter.setTimeZone(timeZone);
            date = formatter.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

}
