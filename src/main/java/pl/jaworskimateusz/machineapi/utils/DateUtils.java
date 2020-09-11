package pl.jaworskimateusz.machineapi.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_SEC_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_SEC_PATTERN);
        return dateTime.format(formatter);
    }

    public static Date stringToDate(String text) {
        try {
            return new SimpleDateFormat(DATE_TIME_PATTERN).parse(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
