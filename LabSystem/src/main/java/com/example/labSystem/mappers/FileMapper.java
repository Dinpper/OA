package com.example.labSystem.mappers;

import com.example.labSystem.dto.FileDto;
import com.example.labSystem.dto.PageRequestQto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    Integer queryCountByPage(PageRequestQto qto);

    List<FileDto> queryFileByPage(PageRequestQto qto);
}
