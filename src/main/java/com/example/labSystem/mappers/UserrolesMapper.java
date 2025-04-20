package com.example.labSystem.mappers;

import com.example.labSystem.domain.Userroles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserrolesMapper {
    Integer insert(Userroles userroles);
}
