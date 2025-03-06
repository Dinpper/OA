package com.example.labSystem.mappers;

import com.example.labSystem.domain.Leave;
import com.example.labSystem.dto.LeaveDto;
import com.example.labSystem.dto.PageRequestQto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveMapper {
    Integer addLeave(LeaveDto qto);

    Integer approveLeave(LeaveDto qto);

    Integer notApprovedLeave(LeaveDto qto);

    Integer queryLeaveCountByPage(PageRequestQto qto);

    List<LeaveDto> queryLeaveByPage(PageRequestQto qto);


}
