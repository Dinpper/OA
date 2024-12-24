package com.example.labSystem.mappers;


import com.example.labSystem.dto.CommonRequestQto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordMapper {
    Integer queryStatusType(CommonRequestQto qto);

    int insert(Record record);

    int attendanceCheckIn(Record record);

    int attendanceCheckOut(Record record);


}
