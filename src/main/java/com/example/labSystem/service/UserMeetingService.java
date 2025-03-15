package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.UserMeetingDto;

import java.util.List;

public interface UserMeetingService {
    void acceptMeeting(UserMeetingDto dto);

    void refuseMeeting(UserMeetingDto dto);

    void checkInMeeting(UserMeetingDto dto);

    void checkOutMeeting(UserMeetingDto dto);

    List<String> queryMeetingDateByMonth(CommonRequestQto qto);
}
