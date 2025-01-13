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

    Double querySignDurationToDayAll(CommonRequestQto qto);
    Double querySignDurationWeekAll(CommonRequestQto qto);
    Double querySignDurationMonthAll(CommonRequestQto qto);

    List<RecordSonDto> querySignDurationWeek(CommonRequestQto qto);

    Integer querySignDurationWeekByUser(CommonRequestQto qto);

    List<RecordSonDto> querySignDurationWeekByGroup(CommonRequestQto qto);

    RecordDto queryOverdueSignInRecords();

    Integer updateIsReminded(@Param("recordId") String recordId);

    Integer queryCountByPage(CommonRequestQto qto);

    List<RecordExcelDto> queryRecordByPage(CommonRequestQto qto);
}
