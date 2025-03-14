package com.example.labSystem.controller;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.service.RecordService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;

/**
 * 签到相关
 *
 * @author Dinpper
 * @since 2024-12-24 09:41:49
 */
@RestController
@RequestMapping("/record")
@Slf4j
public class RecordController extends BaseController {
    @Autowired
    private RecordService recordService;

    /**
     * 查询签到状态
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryStatusType", method = RequestMethod.POST)
    public void queryStatusType(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} queryStatusType,query = {}", account, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        RecordDto result = recordService.queryStatusType(account);
        log.info("User {} queryStatusType, result = {}", account, result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    /**
     * 用户签到
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
    public void attendanceCheckIn(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} checkIn,query = {}", account, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        recordService.attendanceCheckIn(account);
        log.info("User {} checked in at {}", account, LocalDateTime.now());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "签到成功"));
    }

    /**
     * 用户签出
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
    public void attendanceCheckOut(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} checkOut,query = {}", account, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        recordService.attendanceCheckOut(account);
        log.info("User {} checked out at {}", account, LocalDateTime.now());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "签退成功"));
    }

}