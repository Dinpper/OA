package com.example.labSystem.scheduler;

import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.mappers.SystemConfigMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.service.HolidayDateService;
import com.example.labSystem.service.UserService;
import com.example.labSystem.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Component
@Slf4j
public class ReportTask {

    @Autowired
    private EmailService emailService;

    @Autowired
    private HolidayDateService holidayDateService;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 每天晚上 11 点执行，检查是否发送日报/周报
     */

//    @Scheduled(cron = "0 20 2 * * ?")
    @Scheduled(cron = "0 0 23 * * ?", zone = "Asia/Shanghai") // 每天晚上 23:00 触发
    public void executeReportTask() throws Exception {

        //判断是的跳过节假日
        String isSkipHolidays = systemConfigMapper.queryValueByKey("skip_holidays");
        if (Objects.equals(isSkipHolidays, "1")) {
            //如果是节假日就不执行
            if (holidayDateService.isLegalHoliday(DateUtil.getCurDate())) {
                return;
            }
        }

        String reportType = systemConfigMapper.queryValueByKey("report_type");
        if (Objects.equals(reportType, "1")) {
            log.info("开始发送日报...");
            sendDailyReport();
            log.info("发送日报完毕...");
        } else if (Objects.equals(reportType, "2")) {
            DayOfWeek today = LocalDate.now().getDayOfWeek(); // 获取今天是星期几
            if (today == DayOfWeek.SUNDAY) { // 只有周日才执行周报
                log.info("开始发送周报...");
                sendWeeklyReport();
                log.info("发送周报完毕...");
            } else {
                log.info("今天不是周日，跳过周报任务");
            }
        } else if (Objects.equals(reportType, "0")) {
            log.info("系统配置，不发送邮件");
        } else {
            log.info("未定义的任务类型，跳过执行");
        }
    }

    /**
     * 发送日报
     */
    private void sendDailyReport() throws Exception {
        var mapping = emailService.getEmailSenderMapping("1");
        for (Map.Entry<String, List<ReportMessageDto>> stringListEntry : mapping.entrySet()) {
            emailService.sendDailyReportEmail(stringListEntry.getKey(),LocalDate.now(),stringListEntry.getValue());
            log.info("发送日报到:{}",stringListEntry.getKey());
        }
    }

    /**
     * 发送周报
     */
    private void sendWeeklyReport() throws Exception {
        //interimWeeklyReport();
        var mapping = emailService.getEmailSenderMapping("2");
        for (Map.Entry<String, List<ReportMessageDto>> stringListEntry : mapping.entrySet()) {
            emailService.sendWeeklyReportEmail(stringListEntry.getKey(),stringListEntry.getValue());
            log.info("发送周报到:{}",stringListEntry.getKey());
        }
//        List<ReportMessageDto> list = queryWeeklyReport();
//        String to = "dinpper@163.com";
//        emailService.sendWeeklyReportEmail(to, list);
//        log.info("发送日报到" + to);
    }

    //todo 先将数据分装为map，groupId为主键，然后再通过groupId查询拼接个人邮件内容
//    最终可以使用一个 Map<Integer, Group> 来存储每个小组的详细信息，其中：
//    Integer：表示小组的 groupId，作为 Map 的键。
//    Group：表示小组的信息，包括 groupName（小组名称）和 members（小组成员列表）。
    //Map<groupId, GroupDTO>
//    {
//        "1": {
//        "groupId": 1,
//                "groupName": "研究组",
//                "members": [
//        {"userName": "张三", "signDuration": 120},
//        {"userName": "李四", "signDuration": 150}
//        ]
//    },
//        "2": {
//        "groupId": 2,
//                "groupName": "攻防组",
//                "members": [
//        {"userName": "王五", "signDuration": 200},
//        {"userName": "赵六", "signDuration": 180}
//        ]
//    }
//    }


    //临时周报内容 Interim Weekly Bulletin
    void interimWeeklyReport() throws Exception{
        //var mapping = emailService.getEmailSenderMapping("0");
        List<ReportMessageDto> list = queryWeeklyReport();

//        list.forEach(System.out::println);
//        System.out.println("--------------------------------------------------------");
        //liuxuejiao0406@163.com 全部
        String to11 = "liuxuejiao0406@163.com";
        emailService.sendWeeklyReportEmail(to11, list);
        String to12 = "dinpper@163.com";
        emailService.sendWeeklyReportEmail(to12, list);
        log.info("发送日报到:{}，内容为:{}", to11, list);
//        System.out.println("全部");
//        System.out.println(list);
//        System.out.println("--------------------------------------------------------");


        //3539306573@qq.com  2605452642@qq.com
        //        "攻防一组(web)",
        //        "攻防二组(pwn+re)",
        //        "攻防三组(misc+密码)"
        List<ReportMessageDto> res8 = new ArrayList<>();
        list.forEach(l->{
            if(Objects.equals(l.getGroupName(), "攻防一组") || Objects.equals(l.getGroupName(), "攻防二组")
                    || Objects.equals(l.getGroupName(), "攻防三组")){
                res8.add(l);
            }
        });
        String to81 = "1544189298@qq.com";
        emailService.sendWeeklyReportEmail(to81, res8);
        String to82 = "36304612@qq.com";
        emailService.sendWeeklyReportEmail(to82, res8);
        log.info("发送日报到:{}，内容为:{}", to81 + " " + to82, res8);
//        System.out.println("攻防");
//        System.out.println(res8);
//        System.out.println("--------------------------------------------------------");
    }




    public List<ReportMessageDto> queryDailyReport() throws Exception {
        List<ReportMessageDto> resList = new ArrayList<>();
        List<GroupUserDto> GroupUserList = userService.queryAccountListByReportGroup();

        for (GroupUserDto groupUserDto : GroupUserList) {

            ReportMessageDto reportMessageDto = new ReportMessageDto();
            reportMessageDto.setGroupName(groupUserDto.getGroupName());

            List<ReportDto> members = new ArrayList<>();

            for (String account : groupUserDto.getAccountList()) {
                ReportDto reportDto = new ReportDto();

                String userName = usersMapper.queryUserNameByAccount(account);
                reportDto.setUserName(userName);

                Double signDuration = recordMapper.querySignDurationToDayAll(account);
                reportDto.setSignDuration(signDuration == null ? "" : signDuration + "h");

                List<ReportDto> reportDtoList = reportMapper.queryReportDailyByAccount(account);
                StringBuffer workContent = new StringBuffer();
                StringBuffer problems = new StringBuffer();
                StringBuffer plan = new StringBuffer();
                if (reportDtoList != null) {
                    reportDtoList.forEach(l -> {
                        workContent.append(l.getWorkContent()).append(";  ");
                        problems.append(l.getProblems()).append(";  ");
                        plan.append(l.getPlan()).append(";  ");
                    });
                }
                reportDto.setWorkContent(workContent.toString());
                reportDto.setProblems(problems.toString());
                reportDto.setPlan(plan.toString());

                members.add(reportDto);
            }
            reportMessageDto.setMembers(members);
            resList.add(reportMessageDto);
        }

        return resList;
    }

    public List<ReportMessageDto> queryWeeklyReport() throws Exception {
        List<ReportMessageDto> resList = new ArrayList<>();
        List<GroupUserDto> GroupUserList = userService.queryAccountListByReportGroup();

        for (GroupUserDto groupUserDto : GroupUserList) {

            ReportMessageDto reportMessageDto = new ReportMessageDto();
            reportMessageDto.setGroupName(groupUserDto.getGroupName());

            List<ReportDto> members = new ArrayList<>();

            for (String account : groupUserDto.getAccountList()) {
                ReportDto reportDto = new ReportDto();

                String userName = usersMapper.queryUserNameByAccount(account);
                reportDto.setUserName(userName);

                Double signDuration = recordMapper.querySignDurationWeekAll(account);
                reportDto.setSignDuration(signDuration == null ? "" : signDuration + "h");

                List<ReportDto> reportDtoList = reportMapper.queryReportWeeklyByAccount(account);
                StringBuffer workContent = new StringBuffer();
                StringBuffer problems = new StringBuffer();
                StringBuffer plan = new StringBuffer();
                if (reportDtoList != null) {
                    reportDtoList.forEach(l -> {
                        workContent.append(l.getWorkContent()).append(";  ");
                        problems.append(l.getProblems()).append(";  ");
                        plan.append(l.getPlan()).append(";  ");
                    });
                }
                reportDto.setWorkContent(workContent.toString());
                reportDto.setProblems(problems.toString());
                reportDto.setPlan(plan.toString());

                members.add(reportDto);
            }
            reportMessageDto.setMembers(members);
            resList.add(reportMessageDto);
        }

        return resList;
    }


    /**
     * 每天晚上 9 点执行，对未提交 日报/周报 的邮件提醒
     */
    @Scheduled(cron = "0 0 20 * * ?", zone = "Asia/Shanghai") // 每天晚上 20:00 触发
    public void executeReportReminderTask() throws Exception {

        //判断是的跳过节假日
        String isSkipHolidays = systemConfigMapper.queryValueByKey("skip_holidays");
        if (Objects.equals(isSkipHolidays, "1")) {
            //如果是节假日就不执行
            if (holidayDateService.isLegalHoliday(DateUtil.getCurDate())) {
                return;
            }
        }

        String reportType = systemConfigMapper.queryValueByKey("report_type");
        if (Objects.equals(reportType, "1")) {
            log.info("开始提醒日报提交...");
            List<UserDto> userList = reportMapper.queryNoReportSubmittedToday();
            for (UserDto l : userList) {
                emailService.sendReportReminderEmail(l.getEmail(), l.getUserName());
            }
            log.info("提醒日报提交结束...");
        } else if (Objects.equals(reportType, "2")) {
            DayOfWeek today = LocalDate.now().getDayOfWeek(); // 获取今天是星期几
            if (today == DayOfWeek.SUNDAY) { // 只有周日才执行周报
                log.info("开始提醒周报提交...");
                List<UserDto> userList = reportMapper.queryNoReportSubmittedThisWeek();
                for (UserDto l : userList) {
                    emailService.sendReportReminderEmail(l.getEmail(), l.getUserName());
                }
                log.info("提醒周报提交结束...");
            }
        }
    }
}
