package com.example.labSystem.mappers;


import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.RecordExcelDto;
import com.example.labSystem.dto.RecordSonDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RecordMapper {
    Integer queryStatusType(@Param("account") String account);

    int insert(Record record);

    Integer attendanceCheckIn(@Param("account") String account);

    Integer attendanceCheckOut(@Param("account") String account);

    List<RecordSonDto> querySignDurationToDay(CommonRequestQto qto);

    Integer querySignDurationToDayByUser(CommonRequestQto qto);

    List<RecordSonDto> querySignDurationWeek(CommonRequestQto qto);

    Integer querySignDurationWeekByUser(CommonRequestQto qto);

    List<RecordSonDto> querySignDurationWeekByGroup(CommonRequestQto qto);

    RecordDto queryOverdueSignInRecords();

    Integer updateIsReminded(@Param("recordId") String recordId);

    List<RecordExcelDto> queryRecordByPage(CommonRequestQto qto);
}
