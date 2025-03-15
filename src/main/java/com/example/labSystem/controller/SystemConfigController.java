package com.example.labSystem.controller;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.SystemConfigDto;
import com.example.labSystem.service.SystemConfigService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemConfig")
@Slf4j
public class SystemConfigController extends BaseController {

    @Autowired
    private SystemConfigService systemConfigService;

    @RequestMapping(value = "/queryReportType", method = RequestMethod.POST)
    public void queryReportType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = systemConfigService.queryReportType();
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/updateReportType", method = RequestMethod.POST)
    public void updateReportType(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody SystemConfigDto qto) throws Exception {
        systemConfigService.updateReportType(qto.getConfigValue());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "修改成功"));
    }

    @RequestMapping(value = "/queryIsSkipHolidays", method = RequestMethod.POST)
    public void queryIsSkipHolidays(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = systemConfigService.queryIsSkipHolidays();
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/updateIsSkipHolidays", method = RequestMethod.POST)
    public void updateIsSkipHolidays(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody SystemConfigDto qto) throws Exception {
        systemConfigService.updateIsSkipHolidays(qto.getConfigValue());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "修改成功"));
    }
}
