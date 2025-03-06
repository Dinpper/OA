package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.LeaveDto;
import com.example.labSystem.mappers.LeaveMapper;
import com.example.labSystem.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper personalLeaveMapper;


    @Override
    public void addLeave(LeaveDto qto) {
        Integer result = personalLeaveMapper.addLeave(qto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public void approveLeave(LeaveDto qto) {
        Integer result = personalLeaveMapper.approveLeave(qto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public void notApprovedLeave(LeaveDto qto) {
        Integer result = personalLeaveMapper.notApprovedLeave(qto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }
}
