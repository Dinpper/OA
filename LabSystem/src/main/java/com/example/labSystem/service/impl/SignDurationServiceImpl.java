package com.example.labSystem.service.impl;


import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.service.EmailService;
import com.example.labSystem.service.SignDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignDurationServiceImpl implements SignDurationService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public RecordDto queryTodayByUser(CommonRequestQto qto) {
        RecordDto dto = new RecordDto();
        Integer signDuration = recordMapper.querySignDurationByUserAndToDay(qto);
        signDuration = signDuration == null ? 0 :signDuration;
        dto.setSignDuration(signDuration);
        return dto;
    }

//    SignOutReminderService
//
//    @Scheduled(cron = "0 0/30 * * * ?") // 每30分钟执行一次
//    public void checkForSignOutReminder() {
//        // 查询未签退且已签到超过7小时的记录
//        List<SignRecord> overdueRecords = signRecordRepository.findOverdueSignInRecords();
//        for (SignRecord record : overdueRecords) {
//            try {
//                // 获取用户邮箱和姓名（假设存在关联用户信息）
//                String email = record.getUser().getEmail();
//                String name = record.getUser().getName();
//
//                // 发送提醒邮件
//                emailService.sendSignOutReminderEmail(email, name);
//
//                // 可记录日志或标记已提醒状态
//                System.out.println("已发送签退提醒邮件给用户：" + email);
//            } catch (Exception e) {
//                System.err.println("发送签退提醒失败：" + e.getMessage());
//            }
//        }
//    }

}
