package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;

public interface SignDurationService {

    RecordDto queryTodayByUser(CommonRequestQto qto);
}
