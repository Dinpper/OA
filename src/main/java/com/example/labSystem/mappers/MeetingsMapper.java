package com.example.labSystem.mappers;

import com.example.labSystem.domain.Meetings;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.MeetingsDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.dto.ReportDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingsMapper {
    Integer insert(MeetingsDto meetings);

    MeetingsDto queryMeetingNew(@Param("account") String account);

    List<MeetingsDto> queryMeetingByDate(@Param("account") String account, @Param("reportDate") String reportDate);

    Integer update();

    List<MeetingsDto> queryMeetings(CommonRequestQto qto);

    Integer queryCountByPage(PageRequestQto qto);

    List<MeetingsDto> queryMeetingByPage(PageRequestQto qto);

    Integer updateSummary(@Param("meetingId") Integer meetingId, @Param("summary") String summary);

    Integer addKeyword(@Param("meetingId") Integer meetingId, @Param("keyword") String keyword);
}
