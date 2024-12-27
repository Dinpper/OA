package com.example.labSystem.service.impl;


import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.service.SignDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignDurationServiceImpl implements SignDurationService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public RecordDto queryTodayByUser(CommonRequestQto qto) {
        RecordDto dto = new RecordDto();
        Integer signDuration = recordMapper.querySignDurationByUserAndToDay(qto);
        signDuration = signDuration == null ? 0 :signDuration;
        dto.setSignDuration(signDuration);
        return dto;
    }
}
