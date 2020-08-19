package pl.jaworskimateusz.machineapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_PATTERN_ISO = "yyyy-MM-dd'T'HH:mm";
    public static final String DATE_TIME_SEC_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String DATE_PATTERN_SEC = "yyyy-MM-dd";
    public static final String TIME_PATTERN ="HH:mm";

    public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_SEC_PATTERN);
        return dateTime.format(formatter);
    }

    public static Date stringToDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_SEC_PATTERN);
            return new SimpleDateFormat(DATE_TIME_PATTERN).parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
