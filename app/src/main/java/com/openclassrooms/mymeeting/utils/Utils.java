package com.openclassrooms.mymeeting.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Utils {
    private static Date date;
    private static String string;
    private List<String> stringsList;
    static long dateNow;
    private static TimeZone timeZone = TimeZone.getTimeZone("France/Paris");
    /**
     * @param String date pattern: dd/HH/yy HH:mm
     */
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");

    public static String getStringFromDate(Date date) {
        string = formatter.format(date);

        return string;
    }


    public String getStringCurrentDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String dateString = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        return dateString;
    }
    public static Date getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return new Date(year,month,dayOfMonth,9,0);
    }

    /**
     * @param string date pattern: "dd/MM/yyyy HH:mm"
     * @return Date
     */
    public static Date getDateFromString(String string){
        try {
            formatter.setTimeZone(timeZone);
            date = formatter.parse(string);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return date;
    }
    public static Date getRandomDate(){
        long aDay = TimeUnit.DAYS.toMillis(1);
        dateNow =getCurrentDate().getTime();
        Date date1 = new Date(dateNow - aDay*1);
        Date date2 = new Date(dateNow + aDay*7);
        long dateStart = date1.getTime();
        long dateStop = date2.getTime();
        long randomDate = ThreadLocalRandom.current().nextLong(dateStart,dateStop);
        return new Date(randomDate);
    }
}
