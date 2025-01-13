package com.example.labSystem.mappers;

import com.example.labSystem.domain.PersonalLeave;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonalLeaveMapper {
    Integer insert(PersonalLeave personalLeave);
}
