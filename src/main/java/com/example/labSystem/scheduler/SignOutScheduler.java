package com.example.labSystem.scheduler;


import com.example.labSystem.domain.Record;
import com.example.labSystem.mappers.RecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@Slf4j
public class SignOutScheduler {
    @Autowired
    private RecordMapper recordMapper;

    @Scheduled(cron = "0 55 23 * * ?", zone = "Asia/Shanghai") // 每天晚上 23:55 触发
    public void executeSignOutTask() throws Exception {
        log.info("开始自动签退...");
        List<Record> list = recordMapper.queryNotSignOutToday();
        list.forEach(l-> recordMapper.automaticSignOut(l.getRecordId()));
        log.info("自动签退完毕...");
    }

}
