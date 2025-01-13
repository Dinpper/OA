package com.example.labSystem.mappers;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GroupMapper {
    Integer queryCountByPage(CommonRequestQto qto);

    List<GroupDto> queryGroupsByPage(CommonRequestQto qto);
}
