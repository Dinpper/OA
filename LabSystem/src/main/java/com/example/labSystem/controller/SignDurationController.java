package com.example.labSystem.controller;

import cn.idev.excel.FastExcel;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.RecordExcelDto;
import com.example.labSystem.service.RecordService;
import com.example.labSystem.service.SignDurationService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * 签到时长
 *
 * @author Dinpper
 * @since 2024-07-15 09:41:49
 */
@RestController
@RequestMapping("/signDuration")
@Slf4j
public class SignDurationController extends BaseController {
    @Autowired
    private SignDurationService signDurationService;


    /**
     * 查询个人签到时长
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryTodayByUser", method = RequestMethod.POST)
    public void queryTodayByUser(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryTodayByUser,query = " + GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(qto.getAccount())){
            throw new BusinessException(399, "参数错误");
        }
        RecordDto result = signDurationService.queryTodayByUser(qto);
        log.info("queryTodayByUser,result = " + result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    /**
     * 查询个人签到周时长
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryWeek", method = RequestMethod.POST)
    public void queryWeek(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryWeek,query = " + GsonUtil.ObjectToJson(qto));
        RecordDto result = signDurationService.queryWeek(qto);
        log.info("queryWeek,result = " + result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/downLoadExcel", method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryReportByPage,query = {}", GsonUtil.ObjectToJson(qto));
        signDurationService.download(response, qto);
        log.info("queryReportByPage, 导出成功");
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "导出成功"));
    }

}