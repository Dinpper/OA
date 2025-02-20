package com.example.labSystem.controller;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.HarvestByPageDto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.service.HarvestService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Harvest")
@Slf4j
public class HarvestController extends BaseController {

    @Autowired
    private HarvestService harvestService;

    @RequestMapping(value = "/queryHarvestByPage", method = RequestMethod.POST)
    public void queryHarvestByPage(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryHarvestByPage,query = {}", GsonUtil.ObjectToJson(qto));
        HarvestByPageDto result = harvestService.queryHarvestByPage(qto);
        log.info("queryHarvestByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/downloadHarvest", method = RequestMethod.POST)
    public void downloadHarvest(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody CommonRequestQto qto) throws Exception {
        log.info("downloadHarvest,query = {}", GsonUtil.ObjectToJson(qto));
        harvestService.downloadHarvest(response, qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "下载成功"));
    }
}
