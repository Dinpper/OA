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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<ReportTaskDto> list = queryDailyReport();
        String to = "dinpper@163.com";
        emailService.sendDailyReportEmail(to, list);
        log.info("发送日报到" + to);
    }

    /**
     * 发送周报
     */
    private void sendWeeklyReport() throws Exception {
        interimWeeklyReport();
//        List<ReportTaskDto> list = queryWeeklyReport();
//        String to = "dinpper@163.com";
//        emailService.sendWeeklyReportEmail(to, list);
//        log.info("发送日报到" + to);
    }


    //临时周报内容 Interim Weekly Bulletin
    void interimWeeklyReport() throws Exception{
        List<ReportTaskDto> list = queryWeeklyReport();

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


        //1544189298@qq.com   36304612@qq.com
        //        "研究组",
        //        "网络组",
        //        "教育实践",
        //        "实践应用",
        //        "应用开发"
        List<ReportTaskDto> res2 = new ArrayList<>();
        list.forEach(l->{
            if(Objects.equals(l.getGroupName(), "研究组") || Objects.equals(l.getGroupName(), "网络组")
                    || Objects.equals(l.getGroupName(), "教育实践") || Objects.equals(l.getGroupName(), "实践应用")
                    || Objects.equals(l.getGroupName(), "应用开发")){
                res2.add(l);
            }
        });
        String to21 = "1544189298@qq.com";
        emailService.sendWeeklyReportEmail(to21, res2);
        String to22 = "36304612@qq.com";
        emailService.sendWeeklyReportEmail(to22, res2);
        log.info("发送日报到:{}，内容为:{}", to21 + " " + to22, res2);
//        System.out.println("项目组");
//        System.out.println(res2);
//        System.out.println("--------------------------------------------------------");


        //1614351736@qq.com 研究组 1526957795@qq.com
        List<ReportTaskDto> res3 = new ArrayList<>();
        list.forEach(l->{
            if(Objects.equals(l.getGroupName(), "研究组")){
                res3.add(l);
            }
        });
        String to31 = "1544189298@qq.com";
        emailService.sendWeeklyReportEmail(to31, res3);
        String to32 = "36304612@qq.com";
        emailService.sendWeeklyReportEmail(to32, res3);
        log.info("发送日报到:{}，内容为:{}", to31 + " " + to32, res3);
//        System.out.println("研究组");
//        System.out.println(res3);
//        System.out.println("--------------------------------------------------------");


        //3045428098@qq.com  1367637939@qq.com 网络组
        List<ReportTaskDto> res4 = new ArrayList<>();
        list.forEach(l->{
            if(Objects.equals(l.getGroupName(), "网络组")){
                res4.add(l);
            }
        });
        String to41 = "1544189298@qq.com";
        emailService.sendWeeklyReportEmail(to41, res4);
        String to42 = "36304612@qq.com";
        emailService.sendWeeklyReportEmail(to42, res4);
        log.info("发送日报到:{}，内容为:{}", to41 + " " + to42, res4);
//        System.out.println("网络组");
//        System.out.println(res4);
//        System.out.println("--------------------------------------------------------");


        //2941649503@qq.com  781381449@qq.com  教育实践
        List<ReportTaskDto> res5 = new ArrayList<>();
        list.forEach(l->{
            if(Objects.equals(l.getGroupName(), "教育实践")){
                res5.add(l);
            }
        });
        String to51 = "1544189298@qq.com";
        emailService.sendWeeklyReportEmail(to51, res5);
        String to52 = "36304612@qq.com";
        emailService.sendWeeklyReportEmail(to52, res5);
        log.info("发送日报到:{}，内容为:{}", to51 + " " + to52, res5);
//        System.out.println("教育实践");
//        System.out.println(res5);
//        System.out.println("--------------------------------------------------------");


        //2329647588@qq.com  781381449@qq.com  实践应用
        List<ReportTaskDto> res6 = new ArrayList<>();
        list.forEach(l->{
            if(Objects.equals(l.getGroupName(), "实践应用")){
                res6.add(l);
            }
        });
        String to61 = "1544189298@qq.com";
        emailService.sendWeeklyReportEmail(to61, res6);
        String to62 = "36304612@qq.com";
        emailService.sendWeeklyReportEmail(to62, res6);
        log.info("发送日报到:{}，内容为:{}", to61 + " " + to62, res6);
//        System.out.println("实践应用");
//        System.out.println(res6);
//        System.out.println("--------------------------------------------------------");


        //2859876806@qq.com  1162844453@qq.com  应用开发
        List<ReportTaskDto> res7 = new ArrayList<>();
        list.forEach(l->{
            if(Objects.equals(l.getGroupName(), "应用开发")){
                res7.add(l);
            }
        });
        String to71 = "1544189298@qq.com";
        emailService.sendWeeklyReportEmail(to71, res7);
        String to72 = "36304612@qq.com";
        emailService.sendWeeklyReportEmail(to72, res7);
        log.info("发送日报到:{}，内容为:{}", to71 + " " + to72, res7);
//        System.out.println("应用开发");
//        System.out.println(res7);
//        System.out.println("--------------------------------------------------------");


        //3539306573@qq.com  2605452642@qq.com
        //        "攻防一组(web)",
        //        "攻防二组(pwn+re)",
        //        "攻防三组(misc+密码)"
        List<ReportTaskDto> res8 = new ArrayList<>();
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
