package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.UserMeetingDto;
import com.example.labSystem.mappers.UserMeetingsMapper;
import com.example.labSystem.service.UserMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMeetingServiceImpl implements UserMeetingService {

    @Autowired
    private UserMeetingsMapper userMeetingsMapper;

    @Override
    public void acceptMeeting(UserMeetingDto dto) {
        Integer result = userMeetingsMapper.acceptMeeting(dto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public void refuseMeeting(UserMeetingDto dto) {
        Integer result = userMeetingsMapper.refuseMeeting(dto);
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public void checkInMeeting(UserMeetingDto dto) {
        Integer result = userMeetingsMapper.checkInMeeting(dto.getMeetingId(), dto.getAccount());
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public void checkOutMeeting(UserMeetingDto dto) {
        Integer result = userMeetingsMapper.checkOutMeeting(dto.getMeetingId(), dto.getAccount());
        if (result == 0) {
            throw new BusinessException(500, "服务器错误");
        }
    }

    @Override
    public List<String> queryMeetingDateByMonth(CommonRequestQto qto) {
        return userMeetingsMapper.queryMeetingDateByMonth(qto);
    }
}
