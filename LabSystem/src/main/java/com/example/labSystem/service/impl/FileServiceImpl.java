package com.example.labSystem.service.impl;

import com.example.labSystem.Enum.FileTypeEnum;
import com.example.labSystem.dto.FileByPageDto;
import com.example.labSystem.dto.FileDto;
import com.example.labSystem.dto.HarvestDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.mappers.FileMapper;
import com.example.labSystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;


    @Override
    public FileByPageDto queryFileByPage(PageRequestQto qto) {
        FileByPageDto dto = new FileByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());

        if(qto.getFileType() != null) {
            qto.setType(FileTypeEnum.getCodeByTypeName(qto.getFileType()));
        }

        List<FileDto> list = fileMapper.queryFileByPage(qto);
        dto.setFileList(list);
        Integer dataCount = fileMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

}
