package com.example.labSystem.scheduler;

import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.ReportDto;
import com.example.labSystem.dto.ReportTaskDto;
import com.example.labSystem.dto.UserDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.mappers.SystemConfigMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.service.HolidayDateService;
import com.example.labSystem.service.UserService;
import com.example.labSystem.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
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
    @Scheduled(cron = "0 0 23 * * ?") // 每天晚上 23:00 触发
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
        List<ReportTaskDto> list = queryDailyReport();
        String to = "dinpper@163.com";
        emailService.sendDailyReportEmail(to, list);
        log.info("发送日报到" + to);
    }

    /**
     * 发送周报
     */
    private void sendWeeklyReport() throws Exception {
        List<ReportTaskDto> list = queryWeeklyReport();
        String to = "dinpper@163.com";
        emailService.sendWeeklyReportEmail(to, list);
        log.info("发送日报到" + to);
    }

    public List<ReportTaskDto> queryDailyReport() throws Exception {
        List<ReportTaskDto> resList = new ArrayList<>();
        List<GroupUserDto> GroupUserList = userService.queryAccountListByReportGroup();

        for (GroupUserDto groupUserDto : GroupUserList) {

            ReportTaskDto reportTaskDto = new ReportTaskDto();
            reportTaskDto.setGroupName(groupUserDto.getGroupName());

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
            reportTaskDto.setMembers(members);
            resList.add(reportTaskDto);
        }

        return resList;
    }

    public List<ReportTaskDto> queryWeeklyReport() throws Exception {
        List<ReportTaskDto> resList = new ArrayList<>();
        List<GroupUserDto> GroupUserList = userService.queryAccountListByReportGroup();

        for (GroupUserDto groupUserDto : GroupUserList) {

            ReportTaskDto reportTaskDto = new ReportTaskDto();
            reportTaskDto.setGroupName(groupUserDto.getGroupName());

            List<ReportDto> members = new ArrayList<>();

            for (String account : groupUserDto.getAccountList()) {
                ReportDto reportDto = new ReportDto();

                String userName = usersMapper.queryUserNameByAccount(account);
                reportDto.setUserName(userName);

                Double signDuration = recordMapper.querySignDurationToDayAll(account);
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
            reportTaskDto.setMembers(members);
            resList.add(reportTaskDto);
        }

        return resList;
    }


    /**
     * 每天晚上 9 点执行，对未提交 日报/周报 的邮件提醒
     */
    @Scheduled(cron = "0 0 20 * * ?") // 每天晚上 20:00 触发
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
