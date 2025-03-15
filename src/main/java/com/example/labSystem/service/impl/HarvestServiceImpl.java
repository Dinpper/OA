package com.example.labSystem.service.impl;

import com.example.labSystem.domain.Harvest;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.HarvestMapper;
import com.example.labSystem.service.HarvestService;
import com.example.labSystem.utils.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HarvestServiceImpl implements HarvestService {

    @Autowired
    private HarvestMapper harvestMapper;


    @Override
    public HarvestByPageDto queryHarvestByPage(PageRequestQto qto) {
        HarvestByPageDto dto = new HarvestByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<HarvestDto> list = harvestMapper.queryHarvestByPage(qto);
        dto.setHarvestList(list);
        Integer dataCount = harvestMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void downloadHarvest(HttpServletResponse response, CommonRequestQto qto) throws IOException {
        Harvest harvest = harvestMapper.queryHarvestById(qto.getHarvestId());
        FileUtil.downloadFile(response, harvest.getFilePath(), harvest.getFileName());
    }
}
