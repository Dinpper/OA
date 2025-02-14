package com.example.labSystem.mappers;

import com.example.labSystem.domain.Course;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.CourseDro;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseDro> queryCourseByUserList(CommonRequestQto qto);

    Integer queryHasCourse(Course course);

    Integer insert(Course course);

    Integer updateCourse(Course course);
}
