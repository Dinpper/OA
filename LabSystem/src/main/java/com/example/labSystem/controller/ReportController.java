package com.example.labSystem.controller;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.ReportService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 周报相关
 *
 * @author Dinpper
 * @since 2024-01-01 00:59:49
 */
@RestController
@RequestMapping("/record")
@Slf4j
public class ReportController extends BaseController {
    @Autowired
    private ReportService reportService;

    /**
     * 查询签到状态
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryHasDraft", method = RequestMethod.POST)
    public void queryHasDraft(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} queryHasDraft,query = {}", account, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        ReportDto result = reportService.queryHasDraft(account);
        log.info("User {} queryHasDraft, result = {}", account, result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    /**
     * 查询签到状态
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryReportByPage", method = RequestMethod.POST)
    public void queryReportByPage(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} queryReportByPage,query = {}", account, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        ReportByPageDto result = reportService.queryReportByPage(qto);
        log.info("User {} queryReportByPage, result = {}", account, result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }



}