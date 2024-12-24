package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (CockpitCoreData)表服务实现类
 *
 * @author makejava
 * @since 2024-09-10 15:36:16
 */

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;


    /**
     * 新增数据
     *
     * @param qto 实例对象
     * @return 实例对象
     */
    @Override
    public RecordDto queryStatusType(CommonRequestQto qto) {
        RecordDto dto = new RecordDto();
        Integer statusType = recordMapper.queryStatusType(qto);
        if(statusType == null){
            throw new BusinessException(399, "找不到用户");
        }
        dto.setStatusType(statusType);
        return dto;
    }




}
