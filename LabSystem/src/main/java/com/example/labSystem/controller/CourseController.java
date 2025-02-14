package com.example.labSystem.controller;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/reviseCourse", method = RequestMethod.POST)
    public void reviseCourse(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody CommonRequestQto qto) throws Exception {
        courseService.reviseCourse(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "修改成功"));
    }
}
