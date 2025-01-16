package com.example.labSystem.mappers;

import com.example.labSystem.domain.Meetings;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.MeetingsDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.dto.ReportDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingsMapper {
    Integer insert(Meetings meetings);

    Integer update();

    List<MeetingsDto> queryMeetings(CommonRequestQto qto);

    Integer queryCountByPage(PageRequestQto qto);

    List<MeetingsDto> queryMeetingByPage(PageRequestQto qto);
}
