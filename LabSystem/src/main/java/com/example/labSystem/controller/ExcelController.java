package com.example.labSystem.controller;

import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/excel")
@Slf4j
public class ExcelController extends BaseController {
    /**
     * 导入
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryLaborProductivity", method = RequestMethod.POST)
    public void queryLaborProductivity(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.info("queryLaborProductivity,query = " + GsonUtil.ObjectToJson(""));
        log.info("queryLaborProductivity,result = " + "1");

        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, ""));
    }
}
