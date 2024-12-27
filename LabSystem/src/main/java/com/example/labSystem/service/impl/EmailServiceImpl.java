package com.example.labSystem.service.impl;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送签退提醒邮件
     */
    @Override
    public void sendSignOutReminderEmail(String to, String name) throws MessagingException {

        if (StringUtils.isAnyBlank(to, name)) {
            log.info("sendSignOutReminderEmail, query = {}", to + "  " + name);
            throw new BusinessException(502, "to,name不能为空");
        }

        // 构造邮件内容
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("2434714918@qq.com");
        helper.setTo(to);
        helper.setSubject("签退提醒");

        // 设置模板上下文
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("signOutLink", Constants.laboratoryURL);

        // 渲染 HTML 模板
        String emailContent = templateEngine.process("signOutReminder", context);
        System.out.println("Rendered Email Content: " + emailContent);
        // 设置邮件内容
        helper.setText(emailContent, true);

        // 发送邮件
        javaMailSender.send(message);
    }
}
