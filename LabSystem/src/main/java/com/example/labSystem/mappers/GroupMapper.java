package com.example.labSystem.mappers;

import com.example.labSystem.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GroupMapper {
    List<MeetingsDto> queryGroups();

    List<String> queryGroupsList();

    Integer queryCountByPage(PageRequestQto qto);

    List<GroupDto> queryGroupsByPage(PageRequestQto qto);

    Integer updateGroup(GroupDto users);

    Integer deleteGroup(@Param("groupName") String groupName);

    Integer insert(GroupDto users);
}
