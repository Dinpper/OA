package com.example.labSystem.service;

import com.example.labSystem.dto.FileByPageDto;
import com.example.labSystem.dto.PageRequestQto;

public interface FileService {
    FileByPageDto queryFileByPage(PageRequestQto qto);
}
