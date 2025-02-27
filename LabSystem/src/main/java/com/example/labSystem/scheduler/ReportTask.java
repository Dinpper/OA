package com.example.labSystem.scheduler;

import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.ReportDto;
import com.example.labSystem.dto.ReportTaskDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.mappers.SystemConfigMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.service.HolidayDateService;
import com.example.labSystem.service.ReportService;
import com.example.labSystem.service.UserService;
import com.example.labSystem.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportTask {

    @Autowired
    private EmailService emailService; // 假设有个邮件发送服务

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
    @Scheduled(cron = "0 0 23 * * ?") // 每天晚上 23:00 触发
    public void executeReportTask() throws Exception {
        String reportType = systemConfigMapper.queryValueByKey("report_type");
        DayOfWeek today = LocalDate.now().getDayOfWeek(); // 获取今天是星期几

        System.out.println("当前任务类型：" + reportType + "，今天是：" + today);

        //如果是节假日就不执行
        if(holidayDateService.isLegalHoliday(DateUtil.getCurDate())){
            return;
        }

        if ("daily".equals(reportType)) {
            sendDailyReport();
        } else if ("weekly".equals(reportType)) {
            if (today == DayOfWeek.SUNDAY) { // 只有周日才执行周报
                sendWeeklyReport();
            } else {
                System.out.println("今天不是周日，跳过周报任务");
            }
        } else {
            System.out.println("未定义的任务类型，跳过执行");
        }
    }

    /**
     * 发送日报
     */
    private void sendDailyReport() throws Exception {
        List<ReportTaskDto> list = queryDailyReport();
        emailService.sendDailyReportEmail("dinpper@163.com", list);
    }

    /**
     * 发送周报
     */
    private void sendWeeklyReport() throws Exception {
        List<ReportTaskDto> list = queryWeeklyReport();
        emailService.sendDailyReportEmail("dinpper@163.com", list);
    }

    public List<ReportTaskDto> queryDailyReport() throws Exception {
        List<ReportTaskDto> resList = new ArrayList<>();
        List<GroupUserDto> GroupUserList = userService.queryAccountListByGroup();

        for (GroupUserDto groupUserDto : GroupUserList) {

            ReportTaskDto reportTaskDto = new ReportTaskDto();
            reportTaskDto.setGroupName(groupUserDto.getGroupName());

            List<ReportDto> members = new ArrayList<>();

            for (String account : groupUserDto.getAccountList()) {
                ReportDto reportDto = new ReportDto();

                String userName = usersMapper.queryUserNameByAccount(account);
                reportDto.setUserName(userName);

                Double signDuration = recordMapper.querySignDurationToDayAll(account);
                reportDto.setSignDuration(signDuration);

                List<ReportDto> reportDtoList = reportMapper.queryReportDailyByAccount(account);
                StringBuffer workContent = new StringBuffer();
                StringBuffer problems = new StringBuffer();
                StringBuffer plan = new StringBuffer();
                if (reportDtoList != null) {
                    reportDtoList.forEach(l->{
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
        List<GroupUserDto> GroupUserList = userService.queryAccountListByGroup();

        for (GroupUserDto groupUserDto : GroupUserList) {

            ReportTaskDto reportTaskDto = new ReportTaskDto();
            reportTaskDto.setGroupName(groupUserDto.getGroupName());

            List<ReportDto> members = new ArrayList<>();

            for (String account : groupUserDto.getAccountList()) {
                ReportDto reportDto = new ReportDto();

                String userName = usersMapper.queryUserNameByAccount(account);
                reportDto.setUserName(userName);

                Double signDuration = recordMapper.querySignDurationToDayAll(account);
                reportDto.setSignDuration(signDuration);

                List<ReportDto> reportDtoList = reportMapper.queryReportDailyByAccount(account);
                StringBuffer workContent = new StringBuffer();
                StringBuffer problems = new StringBuffer();
                StringBuffer plan = new StringBuffer();
                if (reportDtoList != null) {
                    reportDtoList.forEach(l->{
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
}
