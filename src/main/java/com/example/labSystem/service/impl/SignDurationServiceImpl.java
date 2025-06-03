package com.example.labSystem.service.impl;


import cn.idev.excel.FastExcel;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.SignDurationService;
import com.example.labSystem.utils.DownloadUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SignDurationServiceImpl implements SignDurationService {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public RecordDto queryTodayByUser(CommonRequestQto qto) {
        RecordDto dto = new RecordDto();
        Double signDuration = recordMapper.querySignDurationToDayAll(qto.getAccount());
        signDuration = signDuration == null ? 0 :signDuration;
        dto.setSignDuration(BigDecimal.valueOf(signDuration));
        return dto;
    }

    @Override
    public List<RecordDto> queryWeek(CommonRequestQto qto) {
        List<RecordDto> dtoList = new ArrayList<>();
        List<String> accountList = qto.getList();
        accountList.forEach(l->{
            RecordDto dto = new RecordDto();
            dto.setUserName(l);
            String account = usersMapper.queryAccountByUserName(l);
            List<RecordSonDto> list = recordMapper.querySignDurationWeek(account);
            dto.setWeekList(list);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public RecordDto queryTodayWeekMonth(CommonRequestQto qto) {
        RecordDto dto = new RecordDto();
        Double signDurationToday = recordMapper.querySignDurationToDayAll(qto.getAccount());
        Double signDurationWeek = recordMapper.querySignDurationWeekAll(qto.getAccount());
        Double signDurationMonth = recordMapper.querySignDurationMonthAll(qto.getAccount());
        dto.setSignDurationToday(signDurationToday == null ? 0 : signDurationToday);
        dto.setSignDurationWeek(signDurationWeek == null ? 0 : signDurationWeek);
        dto.setSignDurationMonth(signDurationMonth == null ? 0 : signDurationMonth);
        return dto;
    }

    @Override
    public RecordByPageDto querySignDurationByPage(PageRequestQto qto) throws Exception {
        RecordByPageDto dto = new RecordByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<RecordExcelDto> list = recordMapper.querySignDurationByPage(qto);
        dto.setList(list);
        Integer dataCount = recordMapper.querySignDurationCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void signDurationDownload(HttpServletResponse response, PageRequestQto qto){
        String fileName = "签到记录统计报表";
        String template = Constants.TEMPLATE_PATH + Constants.SIGNDURATION_TEMPLATE_EXCEL_XLSX;
        List<RecordExcelDto> list = recordMapper.querySignDurationByPage(qto);
        DownloadUtil.downloadXlsx(response, fileName, template, list);
    }

}
