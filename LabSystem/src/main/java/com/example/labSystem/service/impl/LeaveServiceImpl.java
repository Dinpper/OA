package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.LeaveByPageDto;
import com.example.labSystem.dto.LeaveDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.mappers.LeaveMapper;
import com.example.labSystem.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;


    @Override
    public void addLeave(LeaveDto qto) throws Exception {
        Integer result = leaveMapper.addLeave(qto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public void approveLeave(LeaveDto qto) throws Exception {
        Integer result = leaveMapper.approveLeave(qto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public void notApprovedLeave(LeaveDto qto) throws Exception {
        Integer result = leaveMapper.notApprovedLeave(qto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public LeaveByPageDto queryPendingLeaveByPage(PageRequestQto qto) throws Exception {
        LeaveByPageDto dto = new LeaveByPageDto();
        //todo 暂时没用需求
        return dto;
    }

    @Override
    public LeaveByPageDto queryLeaveByPage(PageRequestQto qto) throws Exception {
        LeaveByPageDto dto = new LeaveByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<LeaveDto> list = leaveMapper.queryLeaveByPage(qto);
        dto.setLeaveList(list);
        Integer dataCount = leaveMapper.queryLeaveCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

}
