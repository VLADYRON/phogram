package com.phogram.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gavin on 2017. 5. 6..
 */
public class PGTimeUtil {
    private static final Logger log = LoggerFactory.getLogger(PGTimeUtil.class);

    /*Date 을 Timestamp 로 변환*/
    public static Timestamp convertToTimeStamp(Date date , String format) throws Exception{
        if (date == null) {
            return null;
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        return new Timestamp(date.getTime());
    }

    /*문자열 시간을 Date 타입으로 변환*/
    public static Date getTimeDate(String format, String time) throws Exception {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        date = sdf.parse(time);

        return date;
    }

    //지정된 포맷으로 현재 시간을 string 으로 변환해 준다
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
