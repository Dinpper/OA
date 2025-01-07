package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.ReportByPageDto;
import com.example.labSystem.dto.ReportDto;

public interface ReportService {
    ReportDto queryHasDraft(String account);

    ReportByPageDto queryReportByPage(CommonRequestQto qto);
}
