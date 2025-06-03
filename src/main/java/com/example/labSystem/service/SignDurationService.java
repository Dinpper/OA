package com.example.labSystem.service;

import com.example.labSystem.dto.*;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface SignDurationService {

    RecordDto queryTodayByUser(CommonRequestQto qto);

    List<RecordDto> queryWeek(CommonRequestQto qto);

    RecordDto queryTodayWeekMonth(CommonRequestQto qto);

    void signDurationDownload(HttpServletResponse response, PageRequestQto qto);

    RecordByPageDto querySignDurationByPage(PageRequestQto qto) throws Exception;

}
