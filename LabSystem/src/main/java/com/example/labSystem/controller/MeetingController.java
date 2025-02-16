package com.example.labSystem.controller;

import com.example.labSystem.dto.*;
import com.example.labSystem.service.MeetingService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
