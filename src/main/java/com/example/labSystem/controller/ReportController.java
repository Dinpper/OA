package com.example.labSystem.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.ReportService;
import com.example.labSystem.utils.FileUtil;
import com.example.labSystem.utils.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 周报相关
 *
 * @author Dinpper
 * @since 2024-01-01 00:59:49
 */
@RestController
@RequestMapping("/report")
@Slf4j
public class ReportController extends BaseController {
    @Autowired
    private ReportService reportService;

    /**
     * 是否已提交，草稿 ?
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
        if (StringUtils.isEmpty(account)) {
            throw new BusinessException(399, "参数错误");
        }
        ReportDto result = reportService.queryHasDraft(account);
        log.info("User {} queryHasDraft, result = {}", account, result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    /**
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/reportSubmit", method = RequestMethod.POST)
    public void reportSubmit(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "reportJson") String reportJson,
                             @RequestParam(value = "files", required = false) List<MultipartFile> files
                            ) throws Exception {
//        log.info("User {} reportSubmit,query = {}", account, GsonUtil.ObjectToJson(qto));
        Gson gson = new Gson();
        ReportDto toDto = gson.fromJson(reportJson, ReportDto.class);
        reportService.reportSubmit(toDto, files);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "提交成功"));
    }

    @PostMapping("/uploadMultiple")
    public void uploadMultipleFiles(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("files") List<MultipartFile> files) throws Exception {
        FileUtil.uploadBatch(files, "E:\\file");
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "上传成功"));

    }

    /**
     * 报告报表
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryReportByPage", method = RequestMethod.POST)
    public void queryReportByPage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryReportByPage,query = {}", GsonUtil.ObjectToJson(qto));
        ReportByPageDto result = reportService.queryReportByPage(qto);
        log.info("queryReportByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody PageRequestQto qto) throws Exception {
        log.info("download,query = {}", GsonUtil.ObjectToJson(qto));
        reportService.download(response, qto);
        log.info("download, 导出成功");
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "导出成功"));
    }

}