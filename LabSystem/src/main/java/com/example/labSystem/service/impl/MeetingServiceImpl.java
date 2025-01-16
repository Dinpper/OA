package com.example.labSystem.service.impl;

import com.example.labSystem.common.Constants;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.MeetingsMapper;
import com.example.labSystem.service.MeetingService;
import com.example.labSystem.utils.DownloadUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingsMapper meetingsMapper;

    @Override
    public MeetingsByPageDto queryMeetingByPage(PageRequestQto qto) throws Exception {
        MeetingsByPageDto dto = new MeetingsByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<MeetingsDto> list = meetingsMapper.queryMeetingByPage(qto);
        dto.setMeetingsList(list);
        Integer dataCount = meetingsMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void download(HttpServletResponse response, PageRequestQto qto) throws Exception {
        String fileName = "小组统计报表";
        String template = Constants.TEMPLATE_PATH + Constants.GROUP_TEMPLATE_EXCEL_XLSX;
        List<MeetingsDto> list = meetingsMapper.queryMeetingByPage(qto);
        DownloadUtil.downloadXlsx(response, fileName, template, list);
    }
}
