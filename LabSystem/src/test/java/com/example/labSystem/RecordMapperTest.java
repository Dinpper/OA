package com.example.labSystem;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.service.EmailService;
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
    private RecordMapper recordMapper;

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


    @Autowired
    private EmailService emailService;

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

        List<Map<String, Object>> dailyReports = new ArrayList<>();

// 组A
        Map<String, Object> groupA = new HashMap<>();
        groupA.put("groupName", "组A");
        groupA.put("members", List.of(
                Map.of("name", "Alice", "work", "完成实验1分析，整理实验数据。",
                        "issues", "分析中数据误差较大，需重新校验。",
                        "plan", "明天完成数据校验和实验2设计。"),
                Map.of("name", "Bob", "work", "完成数据处理脚本编写。",
                        "issues", "处理效率较低，优化方案待研究。",
                        "plan", "研究算法优化，提高处理效率。"),
                Map.of("name", "Charlie", "work", "完成实验设备维护。",
                        "issues", "部分设备老化，影响实验进度。",
                        "plan", "联系供应商获取零件报价。")
        ));
        dailyReports.add(groupA);

// 组B
        Map<String, Object> groupB = new HashMap<>();
        groupB.put("groupName", "组B");
        groupB.put("members", List.of(
                Map.of("name", "David", "work", "撰写实验报告初稿。",
                        "issues", "报告中部分数据来源不明确。",
                        "plan", "与组员确认数据来源并修订报告。"),
                Map.of("name", "Eve", "work", "完成实验2数据收集。",
                        "issues", "数据采集仪器偶尔发生错误。",
                        "plan", "检查仪器问题并重复数据采集。"),
                Map.of("name", "Frank", "work", "分析实验2数据趋势。",
                        "issues", "发现部分数据存在异常值，需确认实验条件。",
                        "plan", "与实验负责人沟通异常情况并调整分析方法。")
        ));
        dailyReports.add(groupB);



        try {
            String to = "2434714918@qq.com";
//            String to = "3236570050@qq.com";
            emailService.sendDailyReportEmail(to, dailyReports);
        }catch (BusinessException | MessagingException e){
            log.info(String.valueOf(e));
        }
    }
}
