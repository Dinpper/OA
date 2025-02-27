package com.example.labSystem.service.impl;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.dto.ReportTaskDto;
import com.example.labSystem.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;


@Service
@Component
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String username;

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
        helper.setFrom(username);
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

    /**
     * 发送签退提醒邮件
     */
    @Override
    public void sendReportReminderEmail(String to, String name) throws MessagingException {

        if (StringUtils.isAnyBlank(to, name)) {
            log.info("sendSignOutReminderEmail, query = {}", to + "  " + name);
            throw new BusinessException(502, "to,name不能为空");
        }

        // 构造邮件内容
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(to);
        helper.setSubject("睡觉提醒");

        // 设置模板上下文
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("signOutLink", "https://www.bilibili.com/video/BV1TZ42177mL/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click&vd_source=41ce25b988f4e9328c275e5407014227");

        // 渲染 HTML 模板
        String emailContent = templateEngine.process("reportReminder", context);
        System.out.println("Rendered Email Content: " + emailContent);
        // 设置邮件内容
        helper.setText(emailContent, true);

        // 发送邮件
        javaMailSender.send(message);
    }

    @Override
    public void sendDailyReportEmail(String to, List<ReportTaskDto> dailyReports) throws MessagingException {

        // 构造邮件内容
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(to);
        helper.setSubject("日报");

        // 设置模板上下文
        Context context = new Context();
        context.setVariable("dailyReports", dailyReports);

        // 渲染 HTML 模板
        String emailContent = templateEngine.process("dailyReport", context);
        System.out.println("Rendered Email Content: " + emailContent);
        // 设置邮件内容
        helper.setText(emailContent, true);

        // 发送邮件
        javaMailSender.send(message);
    }
}
