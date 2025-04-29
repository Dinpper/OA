package com.example.labSystem.mappers;

import com.example.labSystem.domain.FileRecord;
import com.example.labSystem.dto.FileDto;
import com.example.labSystem.dto.PageRequestQto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileRecordMapper {

    Integer fileSubmit(FileRecord fileRecord);

    Integer updateFileSize(@Param("id") String id, @Param("fileSize") String fileSize);

    Integer queryCountByPage(PageRequestQto qto);

    List<FileDto> queryFileByPage(PageRequestQto qto);

    FileDto queryFileMsgById(@Param("id") Integer id);
}
