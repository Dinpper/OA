package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;
import jakarta.servlet.http.HttpServletResponse;

public interface SignDurationService {

    RecordDto queryTodayByUser(CommonRequestQto qto);

    RecordDto queryWeek(CommonRequestQto qto);

    void download(HttpServletResponse response, CommonRequestQto qto) throws Exception;

}
