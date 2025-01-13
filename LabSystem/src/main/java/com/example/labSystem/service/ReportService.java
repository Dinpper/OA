package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.ReportByPageDto;
import com.example.labSystem.dto.ReportDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
    ReportDto queryHasDraft(String account) throws Exception;

    void reportSubmit(CommonRequestQto qto) throws Exception;

    ReportByPageDto queryReportByPage(CommonRequestQto qto) throws Exception;

    void download(HttpServletResponse response, CommonRequestQto qto) throws Exception;
}
