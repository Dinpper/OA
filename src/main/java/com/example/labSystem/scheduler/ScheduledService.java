package com.example.labSystem.scheduler;

import com.example.labSystem.domain.HolidayDate;
import com.example.labSystem.service.HolidayDateService;
import com.example.labSystem.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

/**
 * @author 10186
 */
@Slf4j
@Component
public class ScheduledService implements ApplicationRunner {

    @Autowired
    private HolidayDateService holidayDateService;

    /**
     * 每月25日获取节日信息
     */
    @Scheduled(cron = "0 0 1 25 * ?", zone = "Asia/Shanghai")
    public void getWorkDay() {
        Calendar calendar = Calendar.getInstance();
        HolidayDate today = holidayDateService.getHoliday(DateUtil.getCurDayInt());
        HolidayDate nextday = holidayDateService.getHoliday(DateUtil.getCurDayInt()+1);
        if (today.getStatus() == 3 && nextday.getStatus() != 3) {
            //如果今天是节假日的最后一天（明天不是节假日)，更新节假日信息
            holidayDateService.initWorkDay(calendar.get(Calendar.YEAR));
        }
        if(today.getDay() == 25){
            //每月的25日，更新节假日信息
            log.info("每月25日凌晨一点,更新节假日信息，如果是12月，同时获取下一年的节日信息");
            holidayDateService.initWorkDay(calendar.get(Calendar.YEAR));
            if (calendar.get(Calendar.MONTH) == 11) {
                holidayDateService.initWorkDay(calendar.get(Calendar.YEAR) + 1);
            }
        }
    }


    /**
     * 如果数据库没有初始化，自动初始化数据库，生成当年的节假日信息
     */
    public void autoCheck() {
        List<HolidayDate> holidays = holidayDateService.getHolidays(DateUtil.getCurDate());
        if (holidays == null|| holidays.isEmpty()) {
            int currentYear = LocalDate.now().getYear();
            holidayDateService.initWorkDay(currentYear);
        }
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        autoCheck();
    }

}
