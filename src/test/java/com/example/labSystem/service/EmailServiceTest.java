package com.example.labSystem.service;

import com.example.labSystem.dto.ReportMessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;
    @Test
    void testEmailMapping() throws Exception {

        var result = emailService.getEmailSenderMapping("1");
        System.out.println(result);
    }

    @Test
    void testEmailSend() throws Exception {
        var mapping = emailService.getEmailSenderMapping("1");
        for (Map.Entry<String, List<ReportMessageDto>> stringListEntry : mapping.entrySet()) {
            if(stringListEntry.getKey().startsWith("3204420579@qq.com"))
                emailService.sendDailyReportEmail(stringListEntry.getKey(),stringListEntry.getValue());
        }
    }

}
