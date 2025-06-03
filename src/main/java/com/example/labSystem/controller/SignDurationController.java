package com.example.labSystem.controller;

import cn.idev.excel.FastExcel;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.RecordService;
import com.example.labSystem.service.SignDurationService;
import com.example.labSystem.utils.DownloadUtil;
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
        List<RecordDto> result = signDurationService.queryWeek(qto);
        log.info("queryWeek,result = " + result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/queryTodayWeekMonth", method = RequestMethod.POST)
    public void queryTodayWeekMonth(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryTodayWeekMonth,query = " + GsonUtil.ObjectToJson(qto));
        RecordDto result = signDurationService.queryTodayWeekMonth(qto);
        log.info("queryTodayWeekMonth,result = " + result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/querySignDurationByPage", method = RequestMethod.POST)
    public void querySignDurationByPage(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody PageRequestQto qto) throws Exception {
        log.info("querySignDurationByPage,query = {}", GsonUtil.ObjectToJson(qto));
        RecordByPageDto result = signDurationService.querySignDurationByPage(qto);
        log.info("querySignDurationByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/signDurationDownload", method = RequestMethod.POST)
    public void signDurationDownload(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody PageRequestQto qto) throws Exception {
        try {
            signDurationService.signDurationDownload(response, qto);
            log.info("queryReportByPage, 导出成功");
        } catch (Exception e) {
            log.error("导出失败", e);
            DownloadUtil.writePlainTextError(response, "导出失败：" + e.getMessage());
        }
    }

}