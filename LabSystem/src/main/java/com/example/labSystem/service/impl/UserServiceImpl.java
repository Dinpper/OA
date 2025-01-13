package com.example.labSystem.service.impl;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.UserDto;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public UserDto queryUserMessage(CommonRequestQto qto) {
        String account = qto.getAccount();
        UserDto dto = usersMapper.queryUserMessage(account);
        return dto;
    }

    @Override
    public List<GroupUserDto> queryGroupUserAll() {
        List<Map<String, Object>> rows = usersMapper.queryGroupUserAll();

        // 将查询结果转换为 GroupUserDto
        List<GroupUserDto> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            GroupUserDto dto = new GroupUserDto();
            dto.setGroupName((String) row.get("groupName"));
            String userListStr = (String) row.get("userList");
            List<String> userList = Arrays.asList(userListStr.split(","));
            dto.setUserList(userList);
            list.add(dto);
        }
        return list;
    }
}
