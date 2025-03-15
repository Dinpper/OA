package com.example.labSystem.service;

import com.example.labSystem.dto.LeaveByPageDto;
import com.example.labSystem.dto.LeaveDto;
import com.example.labSystem.dto.PageRequestQto;

public interface LeaveService {
    void addLeave(LeaveDto qto) throws Exception;

    void approveLeave(LeaveDto qto) throws Exception;

    void notApprovedLeave(LeaveDto qto) throws Exception;

    LeaveByPageDto queryPendingLeaveByPage(PageRequestQto qto) throws Exception;

    LeaveByPageDto queryLeaveByPage(PageRequestQto qto) throws Exception;
}
