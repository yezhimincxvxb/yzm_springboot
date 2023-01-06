package com.yzm.helper.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String INCLINED_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final String COMPACT_PATTERN = "yyyyMMddHHmmss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String INCLINED_DATE_PATTERN = "yyyy/MM/dd";
    public static final String COMPACT_DATE_PATTERN = "yyyyMMdd";
    public static final String TIME_PATTERN = "HH:mm:ss";

    private DateUtils() {
    }

    /**
     * 日期时间-->字符串
     *
     * @param temporal 需要转化的日期时间
     * @param pattern  日期时间格式
     */
    public static String format(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    /**
     * 字符串-->日期时间
     *
     * @param localDateTimeStr 日期时间字符串
     * @param pattern          日期时间格式
     */
    public static LocalDateTime parseLocalDateTime(String localDateTimeStr, String pattern) {
        return LocalDateTime.parse(localDateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String localDateStr, String pattern) {
        return LocalDate.parse(localDateStr, DateTimeFormatter.ofPattern(pattern));
    }
    
    public static LocalTime parseLocalTime(String localTimeStr, String pattern) {
        return LocalTime.parse(localTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 当前时间，是否在某时间段内
     *
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     */
    public static boolean currentBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(startDateTime) && now.isBefore(endDateTime);
    }

    public static boolean currentBetween(Date startDate, Date endDate) {
        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        return currentBetween(startDateTime, endDateTime);
    }

    public static boolean currentBetween(String startStr, String endStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INCLINED_PATTERN);
        LocalDateTime startDateTime = LocalDateTime.parse(startStr, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endStr, formatter);
        return currentBetween(startDateTime, endDateTime);
    }

    /**
     * 本月第一天
     */
    public Date firstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 本月第一天
     */
    public Date firstDayOfMonth2() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(localDate.toString());
        } catch (ParseException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(currentBetween("2020/09/01 12:30:45", "2020/10/01 12:30:45"));
    }

}
