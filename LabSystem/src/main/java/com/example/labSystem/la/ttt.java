package com.example.labSystem.la;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ttt {
    public static void main(String[] args) throws IOException {
        // 从国务院网站获取节假日基础信息
        String url = "http://www.gov.cn/xinwen/2023-01/20/content_5780353.htm";
        String json = new String(new URL(url).openStream().readAllBytes());

        // 使用 Jackson 解析 JSON
        ObjectMapper mapper = new ObjectMapper();
        HolidayInfo info = mapper.readValue(json, HolidayInfo.class);

        // 提取法定节假日和工作日信息
        List<Holiday> holidays = info.getHolidays();
        List<Workday> workdays = info.getWorkdays();

        // 打印输出信息
        System.out.println("**节假日：** ");
        for (Holiday holiday : holidays) {
            System.out.println(holiday.getDate() + " " + holiday.getName());
        }

        System.out.println("**工作日：** ");
        for (Workday workday : workdays) {
            System.out.println(workday.getDate() + " " + workday.getName());
        }
    }

    // 节假日信息类
    @Data
    public static class Holiday {
        private String date;
        private String name;

        // 省略 getter 和 setter 方法
    }

    // 工作日信息类
    @Data
    public static class Workday {
        private String date;
        private String name;

        // 省略 getter 和 setter 方法
    }

    // 节假日基础信息类
    @Data
    public static class HolidayInfo {
        private List<Holiday> holidays;
        private List<Workday> workdays;

        // 省略 getter 和 setter 方法
    }
}
