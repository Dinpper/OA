package com.example.labSystem.controller;

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

/**
 * excel文件的导入导出
 *
 * @author Dinpper
 * @since 2024-07-15 09:41:49
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
        log.info("queryStatusType,query = " + GsonUtil.ObjectToJson(qto));
        RecordDto result = recordService.queryStatusType(qto);
        log.info("queryLaborProductivity,result = " + result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }
}