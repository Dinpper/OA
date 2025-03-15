package com.example.labSystem.mappers;

import com.example.labSystem.domain.Course;
import com.example.labSystem.dto.CourseDro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseDro> queryCourseByUserList(@Param("list") List<String> list, @Param("date") String date);

    Integer queryHasCourse(Course course);

    Integer insert(Course course);

    Integer updateCourse(Course course);
}
