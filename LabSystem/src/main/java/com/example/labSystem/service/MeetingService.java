package com.example.labSystem.service;

import com.example.labSystem.domain.FileRecord;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.MeetingsByPageDto;
import com.example.labSystem.dto.MeetingsDto;
import com.example.labSystem.dto.PageRequestQto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MeetingService {
    void addMeeting(MeetingsDto qto);

    void download(HttpServletResponse response, PageRequestQto qto) throws Exception;

    MeetingsByPageDto queryMeetingByPage(PageRequestQto qto) throws Exception;

    MeetingsDto queryMeetingNew(CommonRequestQto qto) throws Exception;

    List<MeetingsDto> queryMeetingByDate(CommonRequestQto qto) throws Exception;

    void uploadMultiple(FileRecord fileRecord, List<MultipartFile> files) throws Exception;

    void updateSummary(MeetingsDto qto) throws Exception;
}
