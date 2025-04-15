package com.example.labSystem.service.impl;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.ReportTaskDto;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.utils.DateUtil;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public void sendSignOutReminderEmail(RecordDto dto) throws MessagingException {

        if (StringUtils.isAnyBlank(dto.getEmail(), dto.getUserName())) {
            log.info("sendSignOutReminderEmail, query = {}", dto.getEmail() + "  " + dto.getUserName());
            throw new BusinessException(502, "to,name不能为空");
        }

        // 构造邮件内容
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(dto.getEmail());
        helper.setSubject("签退提醒");

        // 设置模板上下文
        Context context = new Context();
        context.setVariable("name", dto.getUserName());
        context.setVariable("startDate", dto.getStartDate().substring(11));
        context.setVariable("signDuration", dto.getSignDuration().divide(new BigDecimal(60), 1, RoundingMode.HALF_UP));
        context.setVariable("signOutLink", Constants.laboratoryURL);

        // 渲染 HTML 模板
        String emailContent = templateEngine.process("signOutReminder", context);
        // 设置邮件内容
        helper.setText(emailContent, true);

        // 发送邮件
        javaMailSender.send(message);
    }

    /**
     * 发送报告提交提醒邮件
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
        helper.setSubject("报告提交提醒");


        // 设置模板上下文
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("signOutLink", Constants.laboratoryURL);

        // 渲染 HTML 模板
        String emailContent = templateEngine.process("reportReminder", context);
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
        helper.setSubject(DateUtil.getTodayByMMDD() + "日报");

        // 设置模板上下文
        Context context = new Context();
        context.setVariable("dailyReports", dailyReports);

        // 渲染 HTML 模板
        String emailContent = templateEngine.process("dailyReport", context);
        // 设置邮件内容
        helper.setText(emailContent, true);

        // 发送邮件
        javaMailSender.send(message);
    }

    @Override
    public void sendWeeklyReportEmail(String to, List<ReportTaskDto> WeeklyReports) throws MessagingException {

        // 构造邮件内容
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(to);
        helper.setSubject("周报");

        // 设置模板上下文
        Context context = new Context();
        context.setVariable("WeeklyReports", WeeklyReports);

        // 渲染 HTML 模板
        String emailContent = templateEngine.process("weeklyReport", context);
        // 设置邮件内容
        helper.setText(emailContent, true);

        // 发送邮件
        javaMailSender.send(message);
    }
}
