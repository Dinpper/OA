package com.example.labSystem.controller;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.RecordService;
import com.example.labSystem.utils.GsonUtil;
import com.example.labSystem.utils.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import com.example.labSystem.utils.DownloadUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Value("${ipConfig.labIp}")
    private String labIp;

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
        if (StringUtils.isEmpty(account)) {
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
        if (StringUtils.isEmpty(account)) {
            throw new BusinessException(399, "参数错误");
        }
        //签到通过内网ip访问
        String visitIp = IpUtil.getIpAddr(request);
        if (!Objects.equals(visitIp, labIp)) {
            throw new BusinessException(403, "签到失败，请通过实验室内网签到");
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
        if (StringUtils.isEmpty(account)) {
            throw new BusinessException(399, "参数错误");
        }
        //签到通过内网ip访问
//        String visitIp = IpUtil.getIpAddr(request);
//        if (!Objects.equals(visitIp, labIp)) {
//            throw new BusinessException(403, "签退失败，请通过实验室内网签退");
//        }
        recordService.attendanceCheckOut(account);
        log.info("User {} checked out at {}", account, LocalDateTime.now());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "签退成功"));
    }

    @RequestMapping(value = "/queryRecordByPage", method = RequestMethod.POST)
    public void queryRecordByPage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryRecordByPage,query = {}", GsonUtil.ObjectToJson(qto));
        RecordByPageDto result = recordService.queryRecordByPage(qto);
        log.info("queryRecordByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }
    @RequestMapping(value = "/checkInRecordDownload", method = RequestMethod.POST)
    public void checkInRecordDownload(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody PageRequestQto qto) throws Exception {
        try {
            recordService.checkInRecordDownload(response, qto);
            log.info("queryReportByPage, 导出成功");
        } catch (Exception e) {
            log.error("导出失败", e);
            DownloadUtil.writePlainTextError(response, "导出失败：" + e.getMessage());
        }
    }

    @RequestMapping(value = "/querySignDurationWeek", method = RequestMethod.POST)
    public void querySignDurationWeek(HttpServletRequest request, HttpServletResponse response,
                                      @RequestBody CommonRequestQto qto) throws Exception {

        List<RecordSonDto> resList = recordService.querySignDurationWeek(qto.getAccount());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, resList));
    }

    @RequestMapping(value = "/queryGroupSignDuration", method = RequestMethod.POST)
    public void queryGroupSignDuration(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<RecordSonDto> resList = recordService.queryGroupSignDuration();
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, resList));
    }

}