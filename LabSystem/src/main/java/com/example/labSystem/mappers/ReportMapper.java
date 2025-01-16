package com.example.labSystem.mappers;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.dto.ReportDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    ReportDto queryHasDraft(@Param("account") String account);

    int reportSubmit(CommonRequestQto qto);

    Integer queryCountByPage(PageRequestQto qto);

    List<ReportDto> queryReportByPage(PageRequestQto qto);
}
