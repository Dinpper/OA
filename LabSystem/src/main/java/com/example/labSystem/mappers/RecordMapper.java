package com.example.labSystem.mappers;


import com.example.labSystem.dto.CommonRequestQto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecordMapper {
//    Integer queryStatusType(CommonRequestQto qto);
    Integer queryStatusType(@Param("account") String account);

    int insert(Record record);

    Integer attendanceCheckIn(@Param("account") String account);

    Integer attendanceCheckOut(@Param("account") String account);

    Integer querySignDurationByUserAndToDay(CommonRequestQto qto);

    Integer querySignDurationByUserAndWeek(CommonRequestQto qto);
}
