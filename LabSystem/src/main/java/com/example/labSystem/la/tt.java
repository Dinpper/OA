package com.example.labSystem.la;

import cn.idev.excel.ExcelWriter;
import cn.idev.excel.FastExcel;
import com.example.labSystem.dto.RecordExcelDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import cn.idev.excel.EasyExcel;
import cn.idev.excel.write.metadata.WriteSheet;
import cn.idev.excel.write.metadata.fill.FillConfig;


public class tt {
    public static Date getStartDayTime(Integer day) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, day); //按天加减
        calendar.set(Calendar.HOUR_OF_DAY, 0); //将小时至零
        calendar.set(Calendar.MINUTE, 0); //将分钟至零
        calendar.set(Calendar.SECOND, 0); //将秒至零
        calendar.set(Calendar.MILLISECOND, 0);//将毫秒至零
        return calendar.getTime();
    }
    public static String getToday(String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
    public static Date getLastIntegralTime(Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, day); //按天加减
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
    public static Date getLastDateByHour(Integer day,Integer hour) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, day); //按天加减
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
//    public static String getRequestJsonBody(String url, Object object) {
//
//        // 创建请求
//        HttpRequest request = cn.hutool.http.HttpUtil.createPost(url);
//        // 设置Content-Type
//        try {
//            // 发送请求
//            String data = JSON.toJSONString(object);
//            HttpResponse response = request.body(data).execute();
//            log.info("【标准】响应数据：{}", response.body());
//            return response.body();
//        } catch (Exception e) {
//            log.info("【标准】请求地址：{} || 异常信息：{}", url, e);
//            throw new BusinessException(603);
//        }
//    }

    public static BigDecimal getIncrease(Integer thisValue, Integer lastValue){
        BigDecimal thisV = BigDecimal.valueOf(thisValue);
        BigDecimal lastV = BigDecimal.valueOf(lastValue);
        BigDecimal increase = BigDecimal.ZERO;
        if (lastV.compareTo(BigDecimal.ZERO) != 0) {
            increase = thisV.subtract(lastV).divide(lastV,4, RoundingMode.HALF_UP);
        }
        return increase;
    }
    public static String formatYearMonth(String year, String month) {
        String formattedMonth = String.format("%02d", Integer.parseInt(month));
        return year + "-" + formattedMonth;
    }
    public static boolean isValidYear(String input) {
        String regex = "^(\\d{4})$";
        if (input == null || !input.matches(regex)) {
            return false;
        }
        int year = Integer.parseInt(input);
        return year >= 1000 && year <= 9999; // 可根据实际需求调整范围
    }
    public static boolean isValidMonth(String input) {
        if (input == null || input.length() > 2 || !input.chars().allMatch(Character::isDigit)) {
            return false;
        }
        int month = Integer.parseInt(input);
        return month >= 1 && month <= 12;
    }

    public static void main(String[] args) throws ParseException {

//        Date nowDate = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(nowDate);
//        String queryDate = DateUtil.dateFormatStr(nowDate,"yyyy-MM-dd");
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        if(10 > calendar.get(Calendar.MINUTE)) hour--;
//        if(0 > hour) hour = 0;
//
//        System.out.println(queryDate);
//
//
//
//        calendar.add(Calendar.DAY_OF_MONTH,-1);
//        Date queryD = calendar.getTime();
//        System.out.println(queryD);
//        queryDate = DateUtil.dateFormatStr(queryD,"yyyy-MM-dd");
//        System.out.println(queryDate);
//
//        Map<String, String> params = new HashMap<>();
//
//            params.put("platformCode", "001");
//            params.put("platformCode", "001");
//
//            params.put("city", "杭州市");
//
//        System.out.println(params);
//        System.out.println("------------------------");
//        String qD = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
//        System.out.println(qD);


//        ParkingIncome parkingIncome = new ParkingIncome();
//        parkingIncome.setReportDate(new Date());
//        parkingIncome.setIncome(1564);
//        parkingIncome.setHour(9);
//
//        System.out.println(parkingIncome);
//        System.out.println(new Date());
//
//
//
////        Date reportDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-10-12");
////        System.out.println(reportDate);
//
//
//
////        Date nowDate = new Date();
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(nowDate);
////        calendar.add(Calendar.DAY_OF_MONTH,-1);
////        Date queryD = calendar.getTime();
////        String queryDate = DateUtil.dateFormatStr(queryD,"yyyy-MM-dd");
////
////        System.out.println(queryDate);
////
////        String formattedDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
////        System.out.println(formattedDate);
////
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(formattedDate);
////        System.out.println(date); // 输出 Date 对象
//
//        Integer carNumAll = 0;
//        Integer a= 10,b=20;
//        carNumAll = a+b;
//        System.out.println(carNumAll);
//        DecimalFormat df = new DecimalFormat("0.00");
//        if(carNumAll != 0){
//            Double aa = Double.valueOf(df.format(Double.valueOf(a) / carNumAll));
//            System.out.println(aa);
//            System.out.println(df.format(Double.valueOf(b) / carNumAll));
//        }
//
//        long value = 1234567899; // 示例 long 值
//        double vv = 15615165165.1231;
//        // 创建 DecimalFormat 实例，设置千分位格式
//        DecimalFormat decimalFormat = new DecimalFormat("#,###");
//        DecimalFormat doubleDecimalFormat = new DecimalFormat("#,###.00");
//        // 格式化数字
//        String formattedValue = decimalFormat.format(value);
//
//        // 输出格式化后的结果
//        System.out.println(formattedValue); // 输出: 123,456,789
//        System.out.println(doubleDecimalFormat.format(vv));
//
//        BigDecimal temporaryCarPercentAll = new BigDecimal("0.25");
//        BigDecimal temporaryCarPercentAll111 = new BigDecimal("0.00");
//        BigDecimal fixedCarPercentAll = BigDecimal.ONE.subtract(temporaryCarPercentAll);
//        BigDecimal fixedCarPercentAll111 = BigDecimal.ONE.subtract(temporaryCarPercentAll111);
//        BigDecimal fixedCarPercentAll000 = BigDecimal.ZERO.subtract(temporaryCarPercentAll111);
//        System.out.println(fixedCarPercentAll);
//        System.out.println(fixedCarPercentAll111);
//        System.out.println(fixedCarPercentAll000);
//        String re = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println(re);
//        String rD = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println(rD);
//
//
//        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartDayTime(-1));
//        System.out.println(startTime);
//        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getLastIntegralTime(-1));
//        System.out.println(endTime);
////        Calendar calendar = Calendar.getInstance();
////        calendar.add(Calendar.HOUR_OF_DAY, -1);
////        int hour = calendar.get(Calendar.HOUR_OF_DAY);
////        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getLastDateByHour(-1, hour));
////        System.out.println(time);
//
//
//
//        String r = getToday("yyyy-MM-dd");
//        System.out.println(r);
//        String ree = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println(ree);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.HOUR_OF_DAY, 1);
//        calendar.add(Calendar.HOUR_OF_DAY, -18);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        System.out.println(hour);
//        String reportDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
//        System.out.println(reportDate);
//
//        int h =  Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//        System.out.println(h);
//
//
//        String rr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println(rr);
//
//        String sss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartDayTime(0));
//        System.out.println(sss);
//
//
//        String endTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getLastIntegralTime(0));
//        System.out.println(endTimes);

//        int hour =  Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//        System.out.println(hour);
//        String reportDate1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String startTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartDayTime(0));
//        String endTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getLastDateByHour(0,hour - 1));
//
//        hour = 0;
//        String reportDate2 = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String startTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartDayTime(-1));
//        String endTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getLastDateByHour(0, hour - 1));
//        System.out.println(reportDate1);
//        System.out.println(startTime1);
//        System.out.println(endTime1);
//        System.out.println(reportDate2);
//        System.out.println(startTime2);
//        System.out.println(endTime2);
//
//        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartDayTime(-1));
//        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getLastIntegralTime(-1));
//        System.out.println(startTime);
//        System.out.println(endTime);
//
//
//        Map<String, String> reportParkingTimeQueryParams = Maps.newConcurrentMap();
//
//        reportParkingTimeQueryParams.put("startTime", "2024-10-01 00:00:00");
//        reportParkingTimeQueryParams.put("endTime", "2024-10-31 23:59:59");
//
//
//        System.out.println(reportParkingTimeQueryParams);
//        reportParkingTimeQueryParams.put("startTime", "2024-10-15 00:00:00");
//
//
//        System.out.println(reportParkingTimeQueryParams); // 输出: 2024-10-15 00:00:00
//
//
//        String params = RequestUtil.getRequestJsonBody(url, parkingPercent);
//        JsonResultDto result = GsonUtil.jsonToObject(params, JsonResultDto.class);
//        log.info("本次推送占比数据:[{}]", JSONObject.toJSON(result));

//        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(new Date());
//        calendar.set(2024, Calendar.OCTOBER, 29, 0, 30, 0);
//        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
//        int hour;
//        if(nowHour == 0) {
//            hour = 24;
//            calendar.add(Calendar.DAY_OF_MONTH, -1);
//        }else {
//            hour = nowHour;
//        }
////        String queryDate = DateUtil.dateFormatStr(calendar.getTime(), "yyyy-MM-dd");
////        System.out.println(hour);
////        System.out.println(queryDate);
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
////        String previousYearMonth = LocalDate.parse("2024-11" + "-01", formatter).minusMonths(1).format(formatter);
////        System.out.println(previousYearMonth);
//
//
//
//        // 定义日期格式（支持日）
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        String lastMonth = LocalDate.parse("2024-01" + "-01", formatter).minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"));
//        System.out.println(lastMonth);
//
//
//
//        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String lastYear = LocalDate.parse("2024" + "-01-01", formatterYear).minusYears(1).format(DateTimeFormatter.ofPattern("yyyy"));
//        System.out.println(lastYear);
//
//
//        String dateStr = "2024-11";
//
//        LocalDate date = LocalDate.parse(dateStr + "-01", formatterYear);  // 补充默认的日子部分
//        String year = String.valueOf(date.getYear());
//        System.out.println(year);  // 输出 "2024"
//
//        String dateStr1 = "2024-03";
//        String d = dateStr1 + "-01";
//        System.out.println(d);
//
//        int month = LocalDate.parse(d, formatter).getMonthValue();  // 获取月份
//        System.out.println(month);  // 输出 11
//        BigDecimal b = getIncrease(0,0);
//        System.out.println(b);
//
//        String queryDate = "2024-11";
//        queryDate = LocalDate.parse(queryDate + "-01", formatter).format(DateTimeFormatter.ofPattern("yyyy"));
//        System.out.println(queryDate);
//
//        Date date2 = new Date();
//        System.out.println(date2);
//
//        System.out.println(formatYearMonth("2024","3"));
//        System.out.println(formatYearMonth("2024","12"));
//        Integer y = 2024;
//        System.out.println(y + "-" + "02");
//        Map<String, String> userMap = new HashMap<>();
//        userMap.put("reportAccount", "1");
//        userMap.put("loginUserCode", "1");
//        userMap.put("loginUserName", "1");
//        System.out.println(userMap);
//
//        Integer pageSize = 10;
//        Integer pageNo = 2;
//        Integer dataCount = 0;
//        Integer pageCount = (dataCount + pageSize - 1) / pageSize;
//        System.out.println(pageCount);
//
//        System.out.println(String.valueOf(LocalDateTime.now().getMonthValue()));
//
//
//        String y1 = "0";
//        String y2 = "1";
//        String y3 = "as";
//        String y4 = "3";
//        String y5 = "12";
//        String y6 = "13";
//        System.out.println(isValidMonth(y1));
//        System.out.println(isValidMonth(y2));
//        System.out.println(isValidMonth(y3));
//        System.out.println(isValidMonth(y4));
//        System.out.println(isValidMonth(y5));
//        System.out.println(isValidMonth(y6));

        // 当前月份，假设是 2024-12
        String currentMonth = "2024-12";
        LocalDate lastYearSameMonth = LocalDate.parse(currentMonth + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusYears(1);
        String lastYearMonth = lastYearSameMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        System.out.println("去年同月: " + lastYearMonth);  // 输出: 2023-12

        System.out.println(isValidYearMonth("2024-11"));  // true
        System.out.println(isValidYearMonth("2024-13"));  // false
        System.out.println(isValidYearMonth("2024-1"));   // false
        System.out.println(isValidYearMonth("24-11"));    // false
        System.out.println(isValidYearMonth(""));
        System.out.println(isValidYearMonth(" "));
        System.out.println(isValidYearMonth(null));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2024-12-30 00:45:00");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        int count = 0;
        while (count < 12) {
            count++;

            calendar.add(Calendar.HOUR_OF_DAY, -1);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String reportDate;
            if(hour == 0) {
                hour = 24;
                calendar.add(Calendar.HOUR_OF_DAY, -1);
            }
            reportDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            System.out.println(reportDate +"  "+ hour);
            if(hour == 24){
                calendar.add(Calendar.HOUR_OF_DAY, +1);
            }
        }

        Double f1 = (double) (1/2);
        System.out.println(f1);
        Double f2 = (1/(double)2);
        System.out.println(f2);

        LocalDate previousMonthDate = LocalDate.now().minusMonths(1);
        int previousMonth = previousMonthDate.getMonthValue();
        System.out.println(previousMonth);
        int currentYear = LocalDate.now().getYear();
        System.out.println(currentYear);

//        excel();
        System.out.println(getSemester());

        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");
        stringList.add("!");

        // 使用逗号分隔
        String concatenatedString = String.join(",", stringList);
        System.out.println(concatenatedString); // 输出: Hello,World,!
    }

    private static String getSemester() {
        YearMonth yearMonth = YearMonth.now();
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        int semester;
        if (month >= 2 && month <= 8) {
            semester = 2;
        } else {
            semester = 1;
        }
        return year + "-" + semester;
    }
    public static boolean isValidYearMonth(String dateString) {
        // 正则表达式：检查 yyyy-MM 格式
        String regex = "^\\d{4}-(0[1-9]|1[0-2])$";
        // 判断字符串是否符合正则表达式
        return dateString != null && dateString.matches(regex);
    }

    public static void excel() {
        List<RecordExcelDto> list = new ArrayList<>();
//        list.add(new RecordExcelDto("张三丰", new Date(),456));
//        list.add(new RecordExcelDto("张无忌", new Date(),456));
//        list.add(new RecordExcelDto("张岁三", new Date(),456));
//        list.add(new RecordExcelDto("张毅", new Date(),456));
//        list.add(new RecordExcelDto("张天师", new Date(),456));
        String template = "src/main/resources/templates/用户签到模板.xlsx";
        String stuFile = "src/main/resources/templates/用户.xlsx";
        try (ExcelWriter excelWriter = FastExcel.write(stuFile).withTemplate(template).build();){
            WriteSheet writeSheet = EasyExcel.writerSheet().build();

            //填充模板第一部分的列表数据
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(list, fillConfig, writeSheet);

            //填充列表下面的total
            Map<String, Object> extraData = new HashMap<>();
            extraData.put("total", list.size());
            excelWriter.fill(extraData, writeSheet);
        }
    }
}
