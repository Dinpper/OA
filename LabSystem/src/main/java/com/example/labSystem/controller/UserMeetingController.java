package com.example.labSystem.controller;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.dto.UserMeetingDto;
import com.example.labSystem.service.UserMeetingService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userMeeting")
@Slf4j
public class UserMeetingController extends BaseController {

    @Autowired
    private UserMeetingService userMeetingService;

    @RequestMapping(value = "/acceptMeeting", method = RequestMethod.POST)
    public void acceptMeeting(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody UserMeetingDto qto) throws Exception {
        log.info("meeting acceptMeeting,query = {}", GsonUtil.ObjectToJson(qto));
        userMeetingService.acceptMeeting(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "接受成功"));
    }

    @RequestMapping(value = "/refuseMeeting", method = RequestMethod.POST)
    public void refuseMeeting(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody UserMeetingDto qto) throws Exception {
        log.info("meeting refuseMeeting,query = {}", GsonUtil.ObjectToJson(qto));
        userMeetingService.refuseMeeting(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "拒绝成功"));
    }

    @RequestMapping(value = "/checkInMeeting", method = RequestMethod.POST)
    public void checkInMeeting(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody UserMeetingDto qto) throws Exception {
        log.info("meeting checkInMeeting,query = {}", GsonUtil.ObjectToJson(qto));
        userMeetingService.checkInMeeting(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "会议签到成功"));
    }

    @RequestMapping(value = "/checkOutMeeting", method = RequestMethod.POST)
    public void checkOutMeeting(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody UserMeetingDto qto) throws Exception {
        log.info("meeting checkOutMeeting,query = {}", GsonUtil.ObjectToJson(qto));
        userMeetingService.checkOutMeeting(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "会议签退成功"));
    }

    @RequestMapping(value = "/queryMeetingDateByMonth", method = RequestMethod.POST)
    public void queryMeetingDateByMonth(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody CommonRequestQto qto) throws Exception {
        log.info("meeting queryMeetingDateByMonth,query = {}", GsonUtil.ObjectToJson(qto));
        List<String> resList = userMeetingService.queryMeetingDateByMonth(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, resList));
    }
}
