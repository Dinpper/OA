package com.example.labSystem.mappers;

import com.example.labSystem.domain.Leave;
import com.example.labSystem.dto.LeaveDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LeaveMapper {
    Integer addLeave(LeaveDto qto);

    Integer approveLeave(LeaveDto qto);

    Integer notApprovedLeave(LeaveDto qto);
}
