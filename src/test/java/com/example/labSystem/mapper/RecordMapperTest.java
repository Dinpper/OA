package com.example.labSystem.mapper;

import cn.hutool.core.date.DateTime;
import com.example.labSystem.dto.SelectSignDateDto;
import com.example.labSystem.mappers.RecordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class RecordMapperTest {
    @Autowired
    RecordMapper mapper;
    @Test
    public void testRecord(){
        mapper.automaticSignOut(50384);
        mapper.automaticSignOut(50402);
        mapper.automaticSignOut(50411);
    }

    @Test
    public void testquerySignDurationToDaySelect(){
        var dto = new SelectSignDateDto();
        dto.setAccount("myot233");
        dto.setDate(LocalDate.now().minusDays(2));
        var result = mapper.querySignDurationToDaySelect(dto);
        System.out.println(result);
    }
}
