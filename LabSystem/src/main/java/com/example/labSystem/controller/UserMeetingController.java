package com.example.labSystem.controller;

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
        log.info("meeting acceptMeeting, 导出成功");
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "导出成功"));
    }
}
