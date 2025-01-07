package com.example.labSystem.service.impl;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.ReportByPageDto;
import com.example.labSystem.dto.ReportDto;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public ReportDto queryHasDraft(String account) {
        ReportDto dto = reportMapper.queryHasDraft(account);
        return dto;
    }

    @Override
    public ReportByPageDto queryReportByPage(CommonRequestQto qto) {
        ReportByPageDto dto = new ReportByPageDto();
        List<ReportDto> list = reportMapper.queryReportByPage(qto);
        dto.setReportList(list);
        return dto;
    }
}
