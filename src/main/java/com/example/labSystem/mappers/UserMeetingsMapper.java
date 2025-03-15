package com.example.labSystem.mappers;

import com.example.labSystem.domain.UserMeeting;
import com.example.labSystem.dto.CommonRequestQto;
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

    Integer checkInMeeting(@Param("meetingId") Integer meetingId, @Param("account") String account);

    Integer checkOutMeeting(@Param("meetingId") Integer meetingId, @Param("account") String account);

    List<String> queryMeetingDateByMonth(CommonRequestQto qto);
}
