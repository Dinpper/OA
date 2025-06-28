package com.example.labSystem.service;

import com.example.labSystem.dto.ReportMessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
        for(int i = 2;i >=1;i--){
            var queryDate = LocalDate.now().minusDays(i);
            var mapping = emailService.getEmailSenderMappingByDate("1", queryDate);
            for (Map.Entry<String, List<ReportMessageDto>> stringListEntry : mapping.entrySet()) {
                while (stringListEntry.getKey().equals("1544189298@qq.com")) {
                    emailService.sendDailyReportEmail(stringListEntry.getKey(), queryDate, stringListEntry.getValue());
                    System.out.println(stringListEntry.getKey() + "sent");
                    Thread.sleep(5000);
                }

            }
        }
    }

}
