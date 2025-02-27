package com.example.labSystem.service;

import com.example.labSystem.dto.UserMeetingDto;

public interface UserMeetingService {
    void acceptMeeting(UserMeetingDto dto);

    void refuseMeeting(UserMeetingDto dto);

    void checkInMeeting(UserMeetingDto dto);

    void checkOutMeeting(UserMeetingDto dto);
}
