package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.mappers.SystemConfigMapper;
import com.example.labSystem.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public String queryReportType() {
        return systemConfigMapper.queryValueByKey("report_type");
    }

    @Override
    public void updateReportType(String value) {
        Integer result = systemConfigMapper.updateValue("report_type", value);
        if (result != 1) {
            throw new BusinessException(500, "修改失败");
        }
    }

    @Override
    public String queryIsSkipHolidays() {
        return systemConfigMapper.queryValueByKey("skip_holidays");
    }

    @Override
    public void updateIsSkipHolidays(String value) {
        Integer result = systemConfigMapper.updateValue("skip_holidays", value);
        if (result != 1) {
            throw new BusinessException(500, "修改失败");
        }
    }
}
