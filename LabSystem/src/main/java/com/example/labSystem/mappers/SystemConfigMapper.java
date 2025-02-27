package com.example.labSystem.mappers;

import com.example.labSystem.domain.SystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemConfigMapper {
    Integer insert(SystemConfig systemConfig);

    String queryValueByKey(@Param("config_key") String config_key);

    String updateValue(@Param("config_key") String config_key, @Param("config_value") String config_value);
}
