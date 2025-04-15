package com.example.labSystem.scheduler;

import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SignOutReminderScheduler {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private EmailService emailService;

    // 每5分钟执行一次
    @Scheduled(cron = "0 */5 * * * ?", zone = "Asia/Shanghai")
    public void executeSignOutReminderTask() throws Exception {
        log.info("检测签退提醒");
        List<RecordDto> list = recordMapper.SignOutReminderUserList();
        for (RecordDto l : list){
            emailService.sendSignOutReminderEmail(l);
            recordMapper.updateIsReminded(l.getRecordId());
            log.info("签退提醒：{}", l.getUserName());
        }
    }

}