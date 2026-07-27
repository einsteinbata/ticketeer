package com.ticketeer.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String getFormattedDate(){
        return getFormattedDate(new Date());
    }

    public static String getFormattedDate(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static LocalDate formatEventDate(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        return LocalDate.parse(dateStr, formatter);
    }

}
