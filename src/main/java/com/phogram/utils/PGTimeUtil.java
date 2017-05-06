package com.phogram.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gavin on 2017. 5. 6..
 */
public class PGTimeUtil {
    private static final Logger log = LoggerFactory.getLogger(PGTimeUtil.class);

    public int getDayOrYearOrMonth(String type) throws Exception {
        Date date = new Date();
        try {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year  = localDate.getYear();
            int month = localDate.getMonthValue();
            int day   = localDate.getDayOfMonth();
            if(type.equals("year")){
                return year;
            }else if(type.equals("day")){
                return day;
            }else if(type.equals("month")){
                return month;
            }else{
                return 0;
            }
        } catch (Exception e) {
            log.error("getDayOrYearOrMonth: "+e);
            throw new Exception(e);
        }
    }

    public static Timestamp convertToTimeStamp(Date date , String format) throws Exception{
        if (date == null)
        {
            return null;
        }
        if (format == null || format.isEmpty())
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        return new Timestamp(date.getTime());
    }

    public static Date getTimeDate(String format, String time) throws Exception {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        date = sdf.parse(time);

        return date;
    }

    //해당 포맷으로 시간을 스트링 형태로 리턴
    public static String nowFormatToString(String format){
        try {
            SimpleDateFormat formatter=new SimpleDateFormat(format);
            return formatter.format(new Date());
        } catch (Exception e) {
            log.error("nowFormatToString: "+e);
            throw e;
        }
    }
}
