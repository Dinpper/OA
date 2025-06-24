package com.example.labSystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;
    @Test
    void testEmailMapping() throws Exception {

        System.out.println(emailService.getEmailSenderMapping("1"));
    }

}
