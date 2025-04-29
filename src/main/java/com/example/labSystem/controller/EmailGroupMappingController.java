package com.example.labSystem.controller;

import com.example.labSystem.dto.EmailGroupMappingDto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.service.EmailGroupMappingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emailGroup")
@Slf4j
public class EmailGroupMappingController extends BaseController {

    @Autowired
    private EmailGroupMappingService emailGroupMappingService;

    @PostMapping("/addEmailGroupByUser")
    public void addEmailGroupByUser(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody EmailGroupMappingDto qto) throws Exception {
        emailGroupMappingService.addEmailGroupByUser(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "发起成功"));
    }

    @PostMapping("/queryEmailGroup")
    public void queryEmailGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<EmailGroupMappingDto> result =  emailGroupMappingService.queryEmailGroup();
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @PostMapping("/updateEmailGroupByUser")
    public void updateEmailGroupByUser(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody EmailGroupMappingDto qto) throws Exception {
        emailGroupMappingService.updateEmailGroupByUser(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "发起成功"));
    }

    @PostMapping("/deleteEmailGroupByUser")
    public void deleteEmailGroupByUser(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody EmailGroupMappingDto qto) throws Exception {
        emailGroupMappingService.deleteEmailGroupByUser(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "发起成功"));
    }
}
