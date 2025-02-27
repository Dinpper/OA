package com.example.labSystem;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.ReportDto;
import com.example.labSystem.dto.ReportTaskDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.mappers.SystemConfigMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.service.HolidayDateService;
import com.example.labSystem.service.UserService;
import com.example.labSystem.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
public class RecordMapperTest {

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

//    @Test
//    public void testQueryStatusType() {
//        CommonRequestQto qto = new CommonRequestQto();
//        qto.setUserCode("testUser");
//        Integer statusType = recordMapper.queryStatusType(qto);
//        System.out.println("Status Type: " + statusType);
//    }



    @Test
    void testRecordMapper() {
        assertNotNull(recordMapper, "RecordMapper should not be null");
    }




    @Test
    void testSendSignOutReminderEmail(){
        try {
//            String to = "1772775873@qq.com";
            String to = "3236570050@qq.com";
            String name = "黎瑞";
            emailService.sendSignOutReminderEmail(to, name);
        }catch (BusinessException | MessagingException e){
            log.info(String.valueOf(e));
        }
    }
    @Test
    void testReportOutReminderEmail(){
        try {
//            String to = "2434714918@qq.com";
            String to = "3236570050@qq.com";
            String name = "黎瑞";
            emailService.sendReportReminderEmail(to, name);
        }catch (BusinessException | MessagingException e){
            log.info(String.valueOf(e));
        }
    }


    @Test
    void testSendDailyReportEmail() throws MessagingException {
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

        emailService.sendDailyReportEmail("dinpper@163.com", resList);
    }
}
