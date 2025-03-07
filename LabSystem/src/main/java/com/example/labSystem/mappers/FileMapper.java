package com.example.labSystem.mappers;

import com.example.labSystem.domain.FileRecord;
import com.example.labSystem.dto.FileDto;
import com.example.labSystem.dto.PageRequestQto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    Integer fileSubmit(FileRecord fileRecord);

    Integer queryCountByPage(PageRequestQto qto);

    List<FileDto> queryFileByPage(PageRequestQto qto);
}
