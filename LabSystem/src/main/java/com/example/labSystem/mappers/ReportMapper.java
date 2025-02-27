package com.example.labSystem.mappers;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.dto.ReportDto;
import com.example.labSystem.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    ReportDto queryHasDraft(@Param("account") String account);

    int reportSubmit(ReportDto qto);

    Integer queryCountByPage(PageRequestQto qto);

    List<ReportDto> queryReportByPage(PageRequestQto qto);

    List<ReportDto> queryReportDailyByAccount(@Param("account") String account);

    List<ReportDto> queryReportWeeklyByAccount(@Param("account") String account);

    List<UserDto> queryNoReportSubmittedToday();

    List<UserDto> queryNoReportSubmittedThisWeek();
}
