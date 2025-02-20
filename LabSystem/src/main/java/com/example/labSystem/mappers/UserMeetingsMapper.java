package com.example.labSystem.mappers;

import com.example.labSystem.domain.UserMeeting;
import com.example.labSystem.dto.UserMeetingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMeetingsMapper {
    Integer insert(UserMeeting userMeetings);

    List<String> queryMembersName(@Param("meetingId") Integer meetingId);

    Integer acceptMeeting(UserMeetingDto dto);

    Integer refuseMeeting(UserMeetingDto dto);
}
