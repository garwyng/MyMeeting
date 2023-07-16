package com.openclassrooms.mymeeting.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    static long dateNow;
    /**
     * @param String date pattern: dd/HH/yy HH:mm
     */

    private static Date date;
    private static String string;
    private static final TimeZone timeZone = TimeZone.getDefault();
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");


    public static String getStringFromDate(Date date) {
        string = formatter.format(date);

        return string;
    }

    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return new Date(year, month, dayOfMonth, 9, 0);
    }

    /**
     * @param string date pattern: "dd/MM/yy HH:mm"
     * @return Date
     */
    public static Date getDateFromString(String string) {
        try {
            formatter.setTimeZone(timeZone);
            date = formatter.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public String getStringCurrentDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String dateString = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        return dateString;
    }
}
