package com.example.labSystem.service;

import com.example.labSystem.domain.EmailGroupMapping;
import com.example.labSystem.dto.EmailGroupMappingDto;

import java.util.List;

public interface EmailGroupMappingService {

    void addEmailGroupByUser(EmailGroupMappingDto dto) throws Exception;

    List<EmailGroupMappingDto> queryEmailGroup() throws Exception;

    void updateEmailGroupByUser(EmailGroupMappingDto dto) throws Exception;

    void deleteEmailGroupByUser(EmailGroupMappingDto dto) throws Exception;
}
