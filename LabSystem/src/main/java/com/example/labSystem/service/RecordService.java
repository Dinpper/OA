package com.example.labSystem.service;


import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (CockpitCoreData)表服务接口
 *
 * @author makejava
 * @since 2024-09-10 15:36:16
 */
public interface RecordService {

    /**
     * 新增数据
     *
     * @param qto 实例对象
     * @return 实例对象
     */
    RecordDto queryStatusType(CommonRequestQto qto);

}
