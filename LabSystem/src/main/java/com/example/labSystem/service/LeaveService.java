package com.example.labSystem.service;

import com.example.labSystem.dto.LeaveDto;

public interface LeaveService {
    void addLeave(LeaveDto qto);

    void approveLeave(LeaveDto qto);

    void notApprovedLeave(LeaveDto qto);
}
