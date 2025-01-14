package com.example.labSystem.controller;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupByPageDto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.service.GroupService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 小组相关
 *
 * @author Dinpper
 * @since 2025-01-14 00:36:49
 */
@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController extends BaseController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/queryGroupsList")
    public void queryGroupsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> result = groupService.queryGroupsList();
        log.info("queryGroupsList, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @PostMapping("/queryGroupsByPage")
    public void queryGroupsByPage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryGroupsByPage,query = {}", GsonUtil.ObjectToJson(qto));
        GroupByPageDto result = groupService.queryGroupsByPage(qto);
        log.info("queryGroupsByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }
}
