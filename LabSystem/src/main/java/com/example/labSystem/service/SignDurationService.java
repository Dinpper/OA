package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordByPageDto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.RecordExcelDto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface SignDurationService {

    RecordDto queryTodayByUser(CommonRequestQto qto);

    RecordDto queryWeek(CommonRequestQto qto);

    RecordDto queryTodayWeekMonth(CommonRequestQto qto);

    void download(HttpServletResponse response, CommonRequestQto qto) throws Exception;

    RecordByPageDto querySignDurationByPage(CommonRequestQto qto) throws Exception;

}
