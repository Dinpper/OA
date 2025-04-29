package com.example.labSystem.service.impl;

import com.example.labSystem.domain.EmailGroupMapping;
import com.example.labSystem.dto.EmailGroupMappingDto;
import com.example.labSystem.mappers.EmailGroupMappingMapper;
import com.example.labSystem.service.EmailGroupMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Component
@Slf4j
public class EmailGroupMappingServiceImpl implements EmailGroupMappingService {

    @Autowired
    private EmailGroupMappingMapper emailGroupMappingMapper;

    @Override
    public void addEmailGroupByUser(EmailGroupMappingDto dto) throws Exception {
        EmailGroupMapping emailGroupMapping = new EmailGroupMapping();
        emailGroupMapping.setAccount(dto.getAccount());
        emailGroupMapping.setUserName(dto.getUserName());
        emailGroupMapping.setEmail(dto.getEmail());
        dto.getGroupList().forEach(l -> {
            emailGroupMapping.setGroupId(l.getGroupId());
            emailGroupMappingMapper.insert(emailGroupMapping);
        });
    }

    @Override
    public List<EmailGroupMappingDto> queryEmailGroup() throws Exception {
        return emailGroupMappingMapper.queryEmailGroupMapping();
    }

    @Override
    public void updateEmailGroupByUser(EmailGroupMappingDto dto) throws Exception {
        deleteEmailGroupByUser(dto);
        addEmailGroupByUser(dto);
    }

    @Override
    public void deleteEmailGroupByUser(EmailGroupMappingDto dto) throws Exception {
        emailGroupMappingMapper.delete(dto.getAccount());
    }
}
