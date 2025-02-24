//package com.example.labSystem.scheduler;
//
//import com.example.labSystem.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Service
//public class ReportTask {
//    @Autowired
//    private ReportScheduleRepository reportScheduleRepository;
//
//    @Autowired
//    private EmailService emailService; // 假设有个邮件发送服务
//
//    /**
//     * 每天晚上 11 点执行，检查是否发送日报/周报
//     */
//    @Scheduled(cron = "0 0 23 * * ?") // 每天晚上 23:00 触发
//    public void executeReportTask() {
//        String reportType = reportScheduleRepository.getCurrentReportType();
//        DayOfWeek today = LocalDate.now().getDayOfWeek(); // 获取今天是星期几
//
//        System.out.println("当前任务类型：" + reportType + "，今天是：" + today);
//
//        if ("daily".equals(reportType)) {
//            sendDailyReport();
//        } else if ("weekly".equals(reportType)) {
//            if (today == DayOfWeek.SUNDAY) { // 只有周日才执行周报
//                sendWeeklyReport();
//            } else {
//                System.out.println("今天不是周日，跳过周报任务");
//            }
//        } else {
//            System.out.println("未定义的任务类型，跳过执行");
//        }
//    }
//
//    /**
//     * 发送日报
//     */
//    private void sendDailyReport() {
//        System.out.println("发送日报：" + LocalDateTime.now());
//        emailService.sendEmail("日报内容");
//    }
//
//    /**
//     * 发送周报
//     */
//    private void sendWeeklyReport() {
//        System.out.println("发送周报：" + LocalDateTime.now());
//        emailService.sendEmail("周报内容");
//    }
//}
