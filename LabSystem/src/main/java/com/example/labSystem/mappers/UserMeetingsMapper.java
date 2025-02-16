package com.example.labSystem.mappers;

import com.example.labSystem.domain.UserMeetings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMeetingsMapper {
    Integer insert(UserMeetings userMeetings);

    List<String> queryMembersName(@Param("meetingId") Integer meetingId);
}
