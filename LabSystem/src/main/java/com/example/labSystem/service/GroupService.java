package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupByPageDto;

import java.util.List;

public interface GroupService {
    List<String> queryGroupsList();

    GroupByPageDto queryGroupsByPage(CommonRequestQto qto);
}
