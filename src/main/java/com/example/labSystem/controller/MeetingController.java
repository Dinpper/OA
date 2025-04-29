package com.example.labSystem.controller;

import com.example.labSystem.domain.FileRecord;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.MeetingService;
import com.example.labSystem.service.SparkManagerService;
import com.example.labSystem.utils.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 会议相关
 *
 * @author Dinpper
 * @since 2025-01-16 17:14:49
 */
@RestController
@RequestMapping("/meeting")
@Slf4j
public class MeetingController extends BaseController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private SparkManagerService sparkManagerService;

    @RequestMapping(value = "/addMeeting", method = RequestMethod.POST)
    public void addMeeting(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody MeetingsDto qto) throws Exception {
        log.info("addMeeting,query = {}", GsonUtil.ObjectToJson(qto));
        meetingService.addMeeting(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "添加成功"));
    }

    @RequestMapping(value = "/queryMeetingNew", method = RequestMethod.POST)
    public void queryMeetingNew(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryMeetingNew,query = {}", GsonUtil.ObjectToJson(qto));
        MeetingsDto result = meetingService.queryMeetingNew(qto);
        log.info("queryMeetingNew, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/queryMeetingByDate", method = RequestMethod.POST)
    public void queryMeetingByDate(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryMeetingByDate,query = {}", GsonUtil.ObjectToJson(qto));
        List<MeetingsDto> result = meetingService.queryMeetingByDate(qto);
        log.info("queryMeetingByDate, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/queryMeetingByPage", method = RequestMethod.POST)
    public void queryMeetingByPage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryMeetingByPage,query = {}", GsonUtil.ObjectToJson(qto));
        MeetingsByPageDto result = meetingService.queryMeetingByPage(qto);
        log.info("queryMeetingByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody PageRequestQto qto) throws Exception {
        log.info("meeting download,query = {}", GsonUtil.ObjectToJson(qto));
        meetingService.download(response, qto);
        log.info("meeting download, 导出成功");
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "导出成功"));
    }

    /**
     *
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/uploadMultiple", method = RequestMethod.POST)
    public void uploadMultiple(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "reportJson") String reportJson,
                               @RequestParam("files") List<MultipartFile> files) throws Exception {
        Gson gson = new Gson();
        FileRecord fileRecord = gson.fromJson(reportJson, FileRecord.class);
        meetingService.uploadMultiple(fileRecord, files);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "提交成功"));
    }

    /**
     *
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/updateSummary", method = RequestMethod.POST)
    public void updateSummary(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody MeetingsDto qto) throws Exception {
        meetingService.updateSummary(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "提交成功"));
    }


    //添加会议关键词
    @RequestMapping(value = "/updateKeyword", method = RequestMethod.POST)
    public void updateKeyword(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody MeetingsDto qto) throws Exception {
        meetingService.updateKeyword(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "提交成功"));
    }

    //添加会议关键词
    @RequestMapping(value = "/getMeetingMinutes", method = RequestMethod.POST)
    public void getMeetingMinutes(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody MeetingsDto qto) throws Exception {
        sparkManagerService.generateMeetingMinutes(qto.getMeetingId());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "添加总结成功"));
    }


}
