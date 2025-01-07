package com.example.labSystem.mappers;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.ReportDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    ReportDto queryHasDraft(@Param("account") String account);

    List<ReportDto> queryReportByPage(CommonRequestQto qto);
}
