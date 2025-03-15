package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.CourseDro;

import java.util.List;

public interface CourseService {
    void reviseCourse(CommonRequestQto qto) throws Exception;

    List<CourseDro> queryCourseByUserList(List<String> list);
}
