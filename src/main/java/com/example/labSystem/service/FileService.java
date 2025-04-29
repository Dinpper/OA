package com.example.labSystem.service;

import com.example.labSystem.dto.FileByPageDto;
import com.example.labSystem.dto.FileDto;
import com.example.labSystem.dto.PageRequestQto;
import jakarta.servlet.http.HttpServletResponse;

public interface FileService {
    FileByPageDto queryFileByPage(PageRequestQto qto);

    void downloadFile(HttpServletResponse response, FileDto qto)throws Exception;
}
