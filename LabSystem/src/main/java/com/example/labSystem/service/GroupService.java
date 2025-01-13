package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupByPageDto;

public interface GroupService {
    GroupByPageDto queryGroupsByPage(CommonRequestQto qto);
}
