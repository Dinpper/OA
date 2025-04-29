package com.example.labSystem.mappers;

import com.example.labSystem.domain.EmailGroupMapping;
import com.example.labSystem.dto.EmailGroupMappingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmailGroupMappingMapper {
    Integer insert(EmailGroupMapping emailGroupMapping);

    List<EmailGroupMappingDto> queryEmailGroupMapping();

    Integer delete(@Param("account") String account);
}
