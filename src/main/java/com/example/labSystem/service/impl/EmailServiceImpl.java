package com.example.labSystem.service.impl;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.EmailGroupMappingMapper;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.service.UserService;
import com.example.labSystem.utils.DateUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.*;
import java.util.stream.Collectors;


@Service
@Component
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private EmailGroupMappingMapper emailGroupMappingMapper;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String username;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class.getName());
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
    public void sendDailyReportEmail(String to, List<ReportMessageDto> dailyReports) throws MessagingException {

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
    public void sendWeeklyReportEmail(String to, List<ReportMessageDto> WeeklyReports) throws MessagingException {

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

    @Override
    public Map<String, List<ReportMessageDto>> getEmailSenderMapping(String reportType) throws Exception {
        var map = new HashMap<String,List<ReportMessageDto>>();
        var receiverGroup = emailGroupMappingMapper.queryEmailGroupMapping();
        var groupMessageMap = queryReportMessage(reportType);
        for (EmailGroupMappingDto emailGroupMappingDto : receiverGroup) {
            var lst = map.getOrDefault(emailGroupMappingDto.getEmail(),new ArrayList<>());
            lst.addAll(emailGroupMappingDto.getGroupList().stream().map((it)->
                            groupMessageMap
                            .get(it.getGroupId()))
                            .toList());
            map.put(emailGroupMappingDto.getEmail(),lst);
        }
        return map;
    }

    /**
     * 查询每日工作日报，并按 groupId 分组组织为 Map 格式，
     * 每个 groupId 映射一个 ReportMessageDto（包含 group 信息 和 members 列表）。
     */
    @Override
    public Map<Integer, ReportMessageDto> queryReportMessage(String reportType) throws Exception {
        // 初始化结果 Map，key 为 groupId，value 为每个小组的详细信息
        Map<Integer, ReportMessageDto> resultMap = new LinkedHashMap<>();

        // 获取每个小组对应的成员账号列表（包含 groupId、groupName、accountList）
        List<GroupUserDto> groupUserList = userService.queryAccountListByReportGroup();

        // 遍历每个小组
        for (GroupUserDto groupUserDto : groupUserList) {
            Integer groupId = groupUserDto.getGroupId();
            String groupName = groupUserDto.getGroupName();

            // 若该 groupId 尚未存在于 Map 中，则初始化一个新的 ReportMessageDto
            ReportMessageDto reportMessageDto = resultMap.getOrDefault(groupId, new ReportMessageDto());
            reportMessageDto.setGroupId(groupId);
            reportMessageDto.setGroupName(groupName);

            // 初始化或获取已有的成员列表
            List<ReportDto> members = reportMessageDto.getMembers() == null ? new ArrayList<>() : reportMessageDto.getMembers();

            // 遍历该组下的所有账号
            for (String account : groupUserDto.getAccountList()) {
                ReportDto reportDto = new ReportDto();

                // 查询用户名
                reportDto.setUserName(usersMapper.queryUserNameByAccount(account));

                Double signDuration = null;
                List<ReportDto> reportDtoList = null;

                if(Objects.equals(reportType, "1")){
                    // 查询今日签到时长（单位：小时）
                    signDuration = recordMapper.querySignDurationToDayAll(account);
                    // 查询该用户当天的日报记录
                    reportDtoList = reportMapper.queryReportDailyByAccount(account);
                }else if(Objects.equals(reportType, "2")){
                    // 查询本周签到时长（单位：小时）
                    signDuration = recordMapper.querySignDurationWeekAll(account);
                    // 查询该用户本周的周报记录
                    reportDtoList = reportMapper.queryReportWeeklyByAccount(account);
                }

                reportDto.setSignDuration(signDuration == null ? "" : signDuration + "h");

                // 汇总多个日报中的工作内容、问题与计划
                StringBuilder workContent = new StringBuilder();
                StringBuilder problems = new StringBuilder();
                StringBuilder plan = new StringBuilder();

                if (reportDtoList != null) {
                    for (ReportDto r : reportDtoList) {
                        workContent.append(r.getWorkContent()).append("; ");
                        problems.append(r.getProblems()).append("; ");
                        plan.append(r.getPlan()).append("; ");
                    }
                }

                // 设置汇总字段
                reportDto.setWorkContent(workContent.toString());
                reportDto.setProblems(problems.toString());
                reportDto.setPlan(plan.toString());

                // 加入成员列表
                members.add(reportDto);
            }

            // 更新成员列表到该小组 DTO 中
            reportMessageDto.setMembers(members);

            // 放入结果 Map（以 groupId 为键）
            resultMap.put(groupId, reportMessageDto);
        }

        // 返回 Map 格式的小组日报信息（方便做个性化邮件、报表等处理）
        return resultMap;
    }
}
