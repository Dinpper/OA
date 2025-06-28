package com.example.labSystem.mappers;

import com.example.labSystem.domain.Report;
import com.example.labSystem.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {


    /**
     * 给timeline接口用的
     * @param account
     * @param year
     * @return
     */
    List<Report> getReportsByAccountAndYear(@Param("account") String account,@Param("year") String year);

    List<Report> getReportsByAccountAndMonth(@Param("account") String account);

    /**
     * 返回用户今年一年的周报
     * @param account 用户账号
     * @return
     */
    List<Report> getReportYearRecords(@Param("account") String account);
    /**
     * 统计某用户今年一年的周报提交次数
     * @param account 用户账号
     * @return
     */
    Integer queryReportSubmitCountYear(@Param("account") String account);
    ReportDto queryHasDraft(@Param("account") String account);

    Integer reportSubmit(ReportDto qto);

    Integer queryCountByPage(PageRequestQto qto);

    List<ReportDto> queryReportByPage(PageRequestQto qto);

    List<ReportDto> queryReportDailyByAccount(@Param("account") String account);
    List<ReportDto> queryReportDailyByAccountAndDate(SelectSignDateDto dtp);
    List<ReportDto> queryReportWeeklyByAccount(@Param("account") String account);

    List<UserDto> queryNoReportSubmittedToday();

    List<UserDto> queryNoReportSubmittedThisWeek();
}
