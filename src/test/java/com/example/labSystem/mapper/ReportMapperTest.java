package com.example.labSystem.mapper;

import com.example.labSystem.dto.SelectSignDateDto;
import com.example.labSystem.mappers.ReportMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
@SpringBootTest
public class ReportMapperTest {
    @Autowired
    ReportMapper mapper;
    @Test
    public void testqueryReportDailyByAccountAndDate(){
        var dto = new SelectSignDateDto();
        dto.setAccount("myot233");
        dto.setDate(LocalDate.now().minusDays(2));
        var result =  mapper.queryReportDailyByAccountAndDate(dto);
        System.out.println(result);
    }
}
