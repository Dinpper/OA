package com.example.labSystem.service;

import com.example.labSystem.dto.MeetingsByPageDto;
import com.example.labSystem.dto.PageRequestQto;
import jakarta.servlet.http.HttpServletResponse;

public interface MeetingService {

    void download(HttpServletResponse response, PageRequestQto qto) throws Exception;

    MeetingsByPageDto queryMeetingByPage(PageRequestQto qto) throws Exception;
}
