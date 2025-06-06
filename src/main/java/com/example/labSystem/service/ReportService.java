package com.example.labSystem.service;

import com.example.labSystem.dto.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReportService {
    ReportDto queryHasDraft(String account) throws Exception;

    void reportSubmit(ReportDto toDto, List<MultipartFile> files) throws Exception;

    ReportByPageDto queryReportByPage(PageRequestQto qto) throws Exception;

    void download(HttpServletResponse response, PageRequestQto qto);
}
