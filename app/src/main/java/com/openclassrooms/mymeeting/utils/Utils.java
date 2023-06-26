package com.openclassrooms.mymeeting.utils;

import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.models.Room;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
    private Date date;
    private static String string;
    private List<String> stringsList;


    public String getStringCurrentDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String dateString = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        return dateString;
    }
    public Calendar getDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return cal;
    }

    /**
     *
     * @param string date
     * @param format FR=pattern{dd/mm/yyyy}
     *                  EN=pattern{yyyy/mm/dd}
     * @return Date
     */
    public Date getDateFromString(String string,String format)throws Exception{

        if (format=="FR"){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            date = formatter.parse(string);
        return date;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
            date = formatter.parse(string);
            return date ;
        }
    }
    public static String getStringFromDate(Calendar date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        string = dateFormat.format(date);

        return string;
    }

}
