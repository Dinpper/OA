package com.example.labSystem.service.impl;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupByPageDto;
import com.example.labSystem.dto.GroupDto;
import com.example.labSystem.mappers.GroupMapper;
import com.example.labSystem.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<String> queryGroupsList() {
        List<String> list = groupMapper.queryGroupsList();
        return list;
    }

    @Override
    public GroupByPageDto queryGroupsByPage(CommonRequestQto qto) {
        GroupByPageDto dto = new GroupByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<GroupDto> list = groupMapper.queryGroupsByPage(qto);
        dto.setGrouplist(list);
        Integer dataCount = groupMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }
}
