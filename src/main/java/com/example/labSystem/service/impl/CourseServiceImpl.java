package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.domain.Course;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.CourseDro;
import com.example.labSystem.mappers.CourseMapper;
import com.example.labSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
@Component
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void reviseCourse(CommonRequestQto qto) throws Exception {
        List<Course> courseList = qto.getCourseList();
        if (courseList == null) {
            throw new BusinessException(399, "参数为空");
        }
        String semester = getSemester();
        courseList.forEach(l -> {
            l.setDate(semester);
            Integer result;
            if (courseMapper.queryHasCourse(l) == 1) {
                result = courseMapper.updateCourse(l);
            } else {
                result = courseMapper.insert(l);
            }
            if (result == 0) {
                throw new BusinessException(500, "修改失败");
            }
        });
    }

    @Override
    public List<CourseDro> queryCourseByUserList(List<String> list) {
//        List<CourseDro> resList = courseMapper.queryCourseByUserList(list, getSemester());
        List<CourseDro> resList = courseMapper.queryCourseByUserList(list, "2021-2");
        return resList;
    }

    //获取课表的时间，年份-学期
    private String getSemester() {
        YearMonth yearMonth = YearMonth.now();
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        int semester;
        if (month >= 2 && month <= 8) {
            semester = 2;
        } else {
            semester = 1;
        }
        return year + "-" + semester;
    }
}
