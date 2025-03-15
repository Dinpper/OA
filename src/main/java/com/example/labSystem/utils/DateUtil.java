package com.example.labSystem.utils;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.OpenApiConstant;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @author zhangyafei
 * @version 2016年5月11日 下午6:47:40
 */
public class DateUtil {

    private final static SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date getData(String a) {
        try {
            return ff.parse(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取当前天，可以自动设置格式
     *
     * @param format
     * @return
     */
    public static String getToday(String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        String dateFormat = sdf.format(date);
        return dateFormat;
    }

    /**
     * 获取两个日期间隔
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @SuppressWarnings("unused")
    public static long[] getDateBetween(Date startDate, Date endDate, long chargingLong) {

        long[] dateLong = new long[4];
        SimpleDateFormat sdf = new SimpleDateFormat(OpenApiConstant.SIMPLEFORMAT24, Locale.CHINA);
        long between = (endDate.getTime() - startDate.getTime()) / 1000;// 除以1000是为了转换成秒
        // System.out.println(between);
        if (chargingLong >= 0) {
            between = chargingLong * 3600 - between;
        }
        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60 / 60;
        dateLong[0] = day1;
        dateLong[1] = hour1;
        dateLong[2] = minute1;
        dateLong[3] = second1;
        return dateLong;
    }

    public static long getDateBetweenMin(Date startDate, Date endDate, long chargingLong) {

        // SimpleDateFormat sdf = new
        // SimpleDateFormat(ManagerConstant.simpleFormat24, Locale.CHINA);
        long between = (endDate.getTime() - startDate.getTime()) / (1000 * 60);// 除以1000是为了转换成秒,转换分钟
        return between;
    }

    public static String getBetweenTime(long[] dateLong) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dateLong.length; i++) {
            long Time = dateLong[i];
            if (i == 0 && Time > 0) {
                builder.append(Time + "天");
            }
            if (i == 1 && Time > 0) {
                builder.append(Time + "小时");
            }
            if (i == 2 && Time > 0) {
                builder.append(Time + "分钟");
            }
            if (i == 3 && Time > 0) {
                builder.append(Time + "秒");
            }
        }
        return builder.toString();
    }

    public static String getBetweenTime2(long[] dateLong) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dateLong.length; i++) {
            long Time = dateLong[i];
            if (i == 0 && Time > 0) {
                builder.append(Time + "天");
            }
            if (i == 1 && Time >= 0) {
                if (Time >= 10) {
                    builder.append(Time + ":");
                } else {
                    builder.append("0" + Time + ":");
                }
            }
            if (i == 2 && Time >= 0) {
                if (Time >= 10) {
                    builder.append(Time + ":");
                } else {
                    builder.append("0" + Time + ":");
                }
            }
            if (i == 3 && Time >= 0) {
                if (Time >= 10) {
                    builder.append(Time);
                } else {
                    builder.append("0" + Time);
                }
            }
        }
        /*
         * if(dateLong[0]==0&&dateLong[1]==0&&dateLong[2]==0){
         * //加入天，时，分都是0.则封装00:00 builder.append("00:00"); }
         */
        return builder.toString();
    }

    public static void main(String arg[]) {


    }
    /**
     * 生成日期
     *
     * @param year
     * @param month
     * @param day
     * @param hrs
     * @param min
     * @param sec
     * @return
     */
    public static Date getDate(int year, int month, int day, int hrs, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hrs, min, sec);
        return cal.getTime();
    }
    /**
     *
     * @Title: areSameDay @Description: 判断是否为同一天 @param @param
     *         dateA @param @param dateB @param @return 设定文件 @return boolean
     *         返回类型 @throws
     */
    public static boolean areSameDay(Date dateA, Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * @Title: dateFormatStr @Description: 按规定格式由date转换String @param @param
     *         date @param @param format @param @return 设定文件 @return String
     *         返回类型 @throws
     */
    public static String dateFormatStr(Date date, String format) {

        return (new SimpleDateFormat(format)).format(date);
    }

    @SuppressWarnings("unused")
    public static Date beforeSixMonthDate() {
        // currentDate 是你要处理的时间
        SimpleDateFormat matter = new SimpleDateFormat(OpenApiConstant.SIMPLEFORMAT24);
        Calendar calendar = Calendar.getInstance();
        // 将calendar装换为Date类型
        Date date = calendar.getTime();
        calendar.add(Calendar.MONTH, -6);
        Date date02 = calendar.getTime();
        return date02;
    }

    /**
     *
     * @Title: afterHourDate @Description: 当前时间延后几小时 @param @param
     *         date @param @param hour @param @return 设定文件 @return Date
     *         返回类型 @throws
     */
    @SuppressWarnings("unused")
    public static Date afterHourDate(Date date, int hour) {
        // currentDate 是你要处理的时间
        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + (hour));
        Date date2 = now.getTime();
        return date2;
    }

    public static Date afterHourMinute(Date date, int minute) {
        // currentDate 是你要处理的时间
        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + (minute));
        Date date2 = now.getTime();
        return date2;
    }

    public static Date StrToDate(String time, String format) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(time);
        } catch (Exception e) {
            throw new BusinessException(998);
        }

        return date;
    }



    @SuppressWarnings("static-access")
    public static Date getEveryDate(Date date, Integer day) {
        // Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }

    /**
     * 锁定时间
     *
     * @param lockTime
     * @return String
     */

    public static String lockTime(long lockTime) {
        long lockTimeMin = lockTime / 60;
        long lockTimeSec = lockTime - lockTimeMin * 60;
        return lockTimeMin + "分" + lockTimeSec + "秒";
    }

    @SuppressWarnings("static-access")
    public static Date getStartDateOrEndDateTody(Date date, String Type) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (!Type.equals("startDate")) {
            calendar.add(calendar.DATE, 1);
        }
        return calendar.getTime();
    }

    public static Date getDateByHour(Date date, Integer hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     *
     * @Title: areSameTimeBetween
     * @Description: 是否超过i小时
     * @param @param dateA
     * @param @param dateB
     * @param @param i
     * @param @return    设定文件
     * @return Boolean    返回类型
     * @throws
     */

    public static Boolean areSameTimeBetween(Date dateA, Date dateB, int i) {
        long[] dateBetween = getDateBetween(dateA, dateB, 0);
        Long hour1 = dateBetween[1];
        Long day = dateBetween[0];
        if (day != null && day > 0) {
            // 超过一天
            return false;
        }
        if (hour1 != null && hour1 > i) {
            // 超过i小时
            return false;
        }
        return true;
    }

    /**
     *
     * @Title: areSameTimeBetweenMin
     * @Description: 时间区间-分钟
     * @param @param dateA
     * @param @param dateB
     * @param @param i
     * @param @return    设定文件
     * @return Boolean    返回类型
     * @throws
     */
    public static Boolean areSameTimeBetweenMin(Date dateA, Date dateB, int i) {
        long[] dateBetween = getDateBetween(dateA, dateB, -1);
        Long hour1 = dateBetween[1];
        Long day = dateBetween[0];
        Long minute = dateBetween[2];
        if (day != null && day > 0) {
            // 超过一天
            return false;
        }
        if (hour1 != null && hour1 > 0) {
            // 超过i小时
            return false;
        }
        if (minute != null && minute > i) {
            // 超过i小时
            return false;
        }
        return true;
    }

    @SuppressWarnings("static-access")
    public static Date getEveryDateMin(Date date, Integer minute) {
        // Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MINUTE, minute);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取当年的第一天
     * @param year
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getEveryDateByDay(Date date, Integer day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }

    public static String getBetweenTimeByInteger(Long between) {
        between = between / 1000;
        long[] dateLong = new long[4];
        if (between < 60) {
            dateLong[0] = 0;
            dateLong[1] = 0;
            dateLong[2] = 1;
            dateLong[3] = 0;
        } else {
            long day1 = between / (24 * 3600);
            long hour1 = between % (24 * 3600) / 3600;
            long minute1 = between % 3600 / 60;
            long second1 = between % 60 / 60;
            dateLong[0] = day1;
            dateLong[1] = hour1;
            dateLong[2] = minute1;
            dateLong[3] = second1;
        }

        return getBetweenTime(dateLong);
    }

    /**
     * 获得该时间的小时
     * @param date
     * @param pars
     * @return
     */
    public static String getHour(String date, String pars) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(StrToDate(date, pars));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return String.valueOf(hour);
    }

    /**
     * 获取该时间的小时
     * @param date
     * @return
     */
    public static String getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return String.valueOf(hour);
    }

    public static Date setHMSZero(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        return date;

    }

    /**
     * 昨天的日期
     *
     * @return
     * @throws ParseException
     */
    public static Date yestDate() throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.DAY_OF_WEEK, -1);
        return parseDate(rightNow.getTime(), "yyyyMMdd");
    }

    public static Date parseDate(Date date, String par) throws ParseException {
        if (null == date || null == par) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(par);
        return format.parse(format.format(date));
    }

    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    public static Date getEveryDateSecond(Date date, Integer second) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }

    public static Date getTodayDate_59(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date addDay(Date date, Integer day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static String getTodayByMMDD() {
        LocalDate today = LocalDate.now();  // 获取今天的日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");  // 定义日期格式
        return today.format(formatter);
    }


    //----------------------------------------------------------

    private static final long MILLS_8_HOUR = 28800000;

    private static final long MILLS_A_DAY = 86400000;

    private static final long MILLS_A_SECOND = 1000;

    private static final int SECONDS_A_HOUR = 3600;

    private static final int SECONDS_A_MINUTE = 60;

    private static final String  DATE_FORMATTER = "yyyy-MM-dd";

    public static final Map<Integer, String> WEEK_MAP = new HashMap<>();

    static {
        WEEK_MAP.put(1, "日");
        WEEK_MAP.put(2, "一");
        WEEK_MAP.put(3, "二");
        WEEK_MAP.put(4, "三");
        WEEK_MAP.put(5, "四");
        WEEK_MAP.put(6, "五");
        WEEK_MAP.put(7, "六");
    }

    /**
     * 根据day,time计算出时间戳
     * @param day 距离北京时间 1970-01-01 00:00:00的天数
     * @param time 距离00:00的秒数
     * @return
     */
    public static long getTimestampByDaySeconds(int day, int time) {
        return day * MILLS_A_DAY - MILLS_8_HOUR + time * MILLS_A_SECOND;
    }

    /**
     * format day int to 'yyyy-MM-dd'
     * @param day
     * @return
     */
    public static String formatDay(int day){
        long time = getTimestampByDaySeconds(day, 0);
        return DateFormatUtils.format(time,DATE_FORMATTER);
    }

    public static int getCurDayInt() {
        return (int) ((System.currentTimeMillis() + MILLS_8_HOUR) / MILLS_A_DAY);
    }


    public static String getCurDate(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER);
        String dateNowStr = sdf.format(d);
        return dateNowStr ;
    }

    public static String getYesterday() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(-1);
        String format = tomorrow.format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
        return format;
    }
    public static String getTomorrow() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        String format = tomorrow.format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
        return format;
    }

}
