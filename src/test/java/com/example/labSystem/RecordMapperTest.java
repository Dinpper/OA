    package com.example.labSystem;

import  com.example.labSystem.common.BusinessException;
import com.example.labSystem.domain.Report;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.*;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.service.HolidayDateService;
import com.example.labSystem.service.SparkManagerService;
import com.example.labSystem.service.UserService;
import com.example.labSystem.service.impl.EmailServiceImpl;
import com.example.labSystem.utils.DingDingUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.apache.commons.codec.binary.Base64;


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

    @Autowired
    private EmailGroupMappingMapper emailGroupMappingMapper;




    @Test
    void testRecordMapper() {
        assertNotNull(recordMapper, "RecordMapper should not be null");
    }



    @Test
    void test1() throws Exception{
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

        resList.forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
        //liuxuejiao0406@163.com 全部
        System.out.println("全部");
        System.out.println(resList);
        System.out.println("--------------------------------------------------------");

        //1544189298@qq.com   36304612@qq.com
        //        "研究组",
        //        "网络组",
        //        "教育实践",
        //        "实践应用",
        //        "应用开发"
        List<ReportMessageDto> res2 = new ArrayList<>();
        resList.forEach(l->{
            if(Objects.equals(l.getGroupName(), "研究组") || Objects.equals(l.getGroupName(), "网络组")
                    || Objects.equals(l.getGroupName(), "教育实践") || Objects.equals(l.getGroupName(), "实践应用")
                    || Objects.equals(l.getGroupName(), "应用开发")){
                res2.add(l);
            }
        });
        System.out.println("项目组");
        System.out.println(res2);
        System.out.println("--------------------------------------------------------");

        //1614351736@qq.com 研究组 1526957795@qq.com
        List<ReportMessageDto> res3 = new ArrayList<>();
        resList.forEach(l->{
            if(Objects.equals(l.getGroupName(), "研究组")){
                res3.add(l);
            }
        });
        System.out.println("研究组");
        System.out.println(res3);
        System.out.println("--------------------------------------------------------");

        //3045428098@qq.com  1367637939@qq.com 网络组
        List<ReportMessageDto> res4 = new ArrayList<>();
        resList.forEach(l->{
            if(Objects.equals(l.getGroupName(), "网络组")){
                res4.add(l);
            }
        });
        System.out.println("网络组");
        System.out.println(res4);
        System.out.println("--------------------------------------------------------");

        //2941649503@qq.com  781381449@qq.com  教育实践
        List<ReportMessageDto> res5 = new ArrayList<>();
        resList.forEach(l->{
            if(Objects.equals(l.getGroupName(), "教育实践")){
                res5.add(l);
            }
        });
        System.out.println("教育实践");
        System.out.println(res5);
        System.out.println("--------------------------------------------------------");
        //2329647588@qq.com  781381449@qq.com  实践应用
        List<ReportMessageDto> res6 = new ArrayList<>();
        resList.forEach(l->{
            if(Objects.equals(l.getGroupName(), "实践应用")){
                res6.add(l);
            }
        });
        System.out.println("实践应用");
        System.out.println(res6);
        System.out.println("--------------------------------------------------------");
        //2859876806@qq.com  1162844453@qq.com  应用开发
        List<ReportMessageDto> res7 = new ArrayList<>();
        resList.forEach(l->{
            if(Objects.equals(l.getGroupName(), "应用开发")){
                res7.add(l);
            }
        });
        System.out.println("应用开发");
        System.out.println(res7);
        System.out.println("--------------------------------------------------------");
        //3539306573@qq.com  2605452642@qq.com
        //        "攻防一组(web)",
        //        "攻防二组(pwn+re)",
        //        "攻防三组(misc+密码)"
        List<ReportMessageDto> res8 = new ArrayList<>();
        resList.forEach(l->{
            if(Objects.equals(l.getGroupName(), "攻防一组") || Objects.equals(l.getGroupName(), "攻防二组")
                    || Objects.equals(l.getGroupName(), "攻防三组")){
                res8.add(l);
            }
        });
        System.out.println("攻防");
        System.out.println(res8);
        System.out.println("--------------------------------------------------------");

        String to = "dinpper@163.com";
        emailService.sendWeeklyReportEmail(to, res8);
        log.info("发送日报到" + to);
    }



    @Test
    void testSendDailyReportEmail() throws MessagingException {
            List<ReportMessageDto> resList = new ArrayList<>();
            List<GroupUserDto> GroupUserList = userService.queryAccountListByReportGroup();

            for (GroupUserDto groupUserDto : GroupUserList) {

                ReportMessageDto ReportMessageDto = new ReportMessageDto();
                ReportMessageDto.setGroupName(groupUserDto.getGroupName());

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
                ReportMessageDto.setMembers(members);
                resList.add(ReportMessageDto);
            }

        emailService.sendDailyReportEmail("dinpper@163.com", resList);
    }


    @Test
    void testQueryEmailGroupMapping() throws Exception {
        List<EmailGroupMappingDto> emailUserList = emailGroupMappingMapper.queryEmailGroupMapping();
        Map<Integer, ReportMessageDto> reportMessageMap = emailService.queryReportMessage("2");
        List<ReportTaskDto> result = new ArrayList<>();

        for (EmailGroupMappingDto emailUser : emailUserList) {
            ReportTaskDto reportTaskDto = new ReportTaskDto();
            reportTaskDto.setUserName(emailUser.getUserName());
            reportTaskDto.setEmail(emailUser.getEmail());
            reportTaskDto.setAccount(emailUser.getAccount());

            List<ReportMessageDto> relatedGroups = new ArrayList<>();
            for (EmailGroupDto groupDto : emailUser.getGroupList()) {
                ReportMessageDto groupReport = reportMessageMap.get(groupDto.getGroupId());
                if (groupReport != null) {
                    relatedGroups.add(groupReport);
                }
            }

            reportTaskDto.setGroupReports(relatedGroups);
            result.add(reportTaskDto);
        }

//        System.out.println(result);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // 格式化输出
        String jsonResult = mapper.writeValueAsString(result);
        System.out.println(jsonResult);


    }


    @Test
    void testDingDing() throws Exception{
        // 钉钉群机器人id号
        String url = "https://oapi.dingtalk.com/robot/send?access_token=d48685c1f9129ee03a31956ce3aea205c9d7f23ef02c68756256151f19ed5581";
        // hello,为关键字
        String msg = "hello, 大家好";
        DingDingUtil.send(url, msg);
    }

    @Test
    void sendTextTest2() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String sign = DingDingUtil.getSign(timestamp);


        // 钉钉群机器人id号
        String baseUrl = "https://oapi.dingtalk.com/robot/send?access_token=d48685c1f9129ee03a31956ce3aea205c9d7f23ef02c68756256151f19ed5581";
        String msg = "这是一条ceshi的消息！";

        String fullUrl =
                String.format(baseUrl + "&timestamp=%s&sign=%s", timestamp, sign);
        DingDingUtil.send(fullUrl, msg);
    }


    @Resource
    private SparkManagerService sparkManager;



    @Autowired
    private MeetingsMapper meetingsMapper;

    @Autowired SparkManagerService sparkManagerService;
    @Test
    public void mm() throws Exception {
            List<ReportMessageDto> list = queryWeeklyReport();

//        list.forEach(System.out::println);
//        System.out.println("--------------------------------------------------------");
            //liuxuejiao0406@163.com 全部
            String to12 = "dinpper@163.com";
            emailService.sendWeeklyReportEmail(to12, list);
//            log.info("发送日报到:{}，内容为:{}", , list);
//        System.out.println("全部");
//        System.out.println(list);
//        System.out.println("--------------------------------------------------------");


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

}
