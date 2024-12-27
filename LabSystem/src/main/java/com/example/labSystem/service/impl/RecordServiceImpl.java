package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author makejava
 * @since 2024-09-10 15:36:16
 */

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private UsersMapper usersMapper;



    @Override
    public RecordDto queryStatusType(String account) {
        RecordDto dto = new RecordDto();
        Integer statusType = recordMapper.queryStatusType(account);
        if(statusType == null){
            //是否存在该用户， 刚创建没有签到记录
            Integer isExist = usersMapper.queryIsUserExist(account);
            if(isExist == 0){
                throw new BusinessException(399, "找不到用户");
            }
            statusType = 1;
        }
        dto.setStatusType(statusType);
        return dto;
    }

    @Override
    public void attendanceCheckIn(String account) {
        Integer result = recordMapper.attendanceCheckIn(account);
        if(result == 0){
            throw new BusinessException(500, "插入失败");
        }
    }

    @Override
    public void attendanceCheckOut(String account) {
        Integer result = recordMapper.attendanceCheckOut(account);
        if(result == 0){
            throw new BusinessException(500, "插入失败");
        }
    }





}
