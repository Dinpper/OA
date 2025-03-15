package com.example.labSystem.service;

import com.example.labSystem.domain.Harvest;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.HarvestByPageDto;
import com.example.labSystem.dto.MeetingsDto;
import com.example.labSystem.dto.PageRequestQto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface HarvestService {

    HarvestByPageDto queryHarvestByPage(PageRequestQto qto);

    void downloadHarvest(HttpServletResponse response,  CommonRequestQto qto) throws IOException;
}
