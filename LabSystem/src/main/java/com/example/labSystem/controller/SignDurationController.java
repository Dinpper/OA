package com.example.labSystem.controller;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.service.RecordService;
import com.example.labSystem.service.SignDurationService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 签到时长
 *
 * @author Dinpper
 * @since 2024-07-15 09:41:49
 */
@RestController
@RequestMapping("/signDuration")
@Slf4j
public class SignDurationController extends BaseController {
    @Autowired
    private SignDurationService signDurationService;


    /**
     * 查询个人签到时长
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/queryTodayByUser", method = RequestMethod.POST)
    public void queryTodayByUser(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody CommonRequestQto qto) throws Exception {
        log.info("queryTodayByUser,query = " + GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(qto.getAccount())){
            throw new BusinessException(399, "参数错误");
        }
        RecordDto result = signDurationService.queryTodayByUser(qto);
        log.info("queryTodayByUser,result = " + result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }
}