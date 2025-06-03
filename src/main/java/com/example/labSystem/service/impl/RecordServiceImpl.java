package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.RecordService;
import com.example.labSystem.utils.DownloadUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dinpper
 * @since 2024-09-10 15:36:16
 */

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public RecordDto queryStatusType(String account) {
        RecordDto dto = new RecordDto();
        Integer statusType = recordMapper.queryStatusType(account);
        if (statusType == null) {
            //是否存在该用户， 刚创建没有签到记录
            Integer isExist = usersMapper.queryIsUserExist(account);
            if (isExist == 0) {
                throw new BusinessException(399, "找不到用户");
            }
            statusType = 1;
        }
        dto.setStatusType(statusType);
        return dto;
    }

    @Override
    public void attendanceCheckIn(String account) {
        Integer statusType = recordMapper.queryStatusType(account);
        if (statusType == null) {
            //是否存在该用户， 刚创建没有签到记录
            Integer isExist = usersMapper.queryIsUserExist(account);
            if (isExist == 0) {
                throw new BusinessException(399, "找不到用户");
            }
        }
        Integer result = recordMapper.attendanceCheckIn(account);
        if (result == 0) {
            throw new BusinessException(500, "签到失败");
        }
    }

    @Override
    public void attendanceCheckOut(String account) {
        Integer statusType = recordMapper.queryStatusType(account);
        if (statusType == null) {
            throw new BusinessException(399, "找不到用户");
        } else if (statusType == 1) {
            throw new BusinessException(500, "未签到");
        }
        Integer result = recordMapper.attendanceCheckOut(account);
        if (result == 0) {
            throw new BusinessException(500, "签退失败");
        }
    }

    @Override
    public RecordByPageDto queryRecordByPage(PageRequestQto qto) throws Exception {
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
        List<RecordExcelDto> list = recordMapper.queryRecordByPage(qto);
        dto.setList(list);
        Integer dataCount = recordMapper.queryRecordCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void download(HttpServletResponse response, PageRequestQto qto){
        String fileName = "签到统计报表";
        String template = Constants.TEMPLATE_PATH + Constants.SIGNDURATION_TEMPLATE_EXCEL_XLSX;
        List<RecordExcelDto> list = recordMapper.queryRecordByPage(qto);
        DownloadUtil.downloadXlsx(response, fileName, template, list);
    }

    @Override
    public List<RecordSonDto> querySignDurationWeek(String account) {
        return recordMapper.querySignDurationWeek(account);
    }

    @Override
    public List<RecordSonDto> queryGroupSignDuration() {
        return recordMapper.queryGroupSignDuration();
    }

}
