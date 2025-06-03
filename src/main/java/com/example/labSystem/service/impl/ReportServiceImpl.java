package com.example.labSystem.service.impl;

import cn.idev.excel.FastExcel;
import com.example.labSystem.Enum.FileTypeEnum;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.domain.FileRecord;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.FileRecordMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.service.ReportService;
import com.example.labSystem.utils.DownloadUtil;
import com.example.labSystem.utils.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private FileRecordMapper fileRecordMapper;

    @Value("${fileStorage.rootDir}")
    private String uploadDir;

    @Override
    public ReportDto queryHasDraft(String account) throws Exception {
        return reportMapper.queryHasDraft(account);
    }

    @Override
    public void reportSubmit(ReportDto dto, List<MultipartFile> files) throws Exception {
        Integer number = reportMapper.reportSubmit(dto);
        if (number != 1) {
            throw new BusinessException(500, "提交失败");
        }
        if (files != null) {
            //通用路径
            String catalog = uploadDir + FileUtil.generateFilePath(FileTypeEnum.getDesc(1));
            FileUtil.createCatalog(catalog);

            FileRecord fileRecord = new FileRecord();
            fileRecord.setUploadedBy(dto.getAccount());
            fileRecord.setVisibility(1);
            fileRecord.setSourceType(1);
            fileRecord.setRelatedId(dto.getReportId());
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                fileRecord.setFileName(fileName);

                String uuid = FileUtil.generateFileNameWithSuffix(fileName);
                fileRecord.setStoredFileName(uuid);

                String filePath = catalog + "/" + uuid;
                fileRecord.setFilePath(filePath);

                fileRecord.setFileMd5(FileUtil.calculateFileMd5(file));

                fileRecord.setFileType(FileUtil.getFileType(fileName));

                //todo 描述
                fileRecord.setDescription("");

                FileUtil.saveFile(file, filePath);

                fileRecordMapper.fileSubmit(fileRecord);

            }
        }
    }

    @Override
    public ReportByPageDto queryReportByPage(PageRequestQto qto) throws Exception {
        ReportByPageDto dto = new ReportByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<ReportDto> list = reportMapper.queryReportByPage(qto);
        dto.setReportList(list);
        Integer dataCount = reportMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void download(HttpServletResponse response, PageRequestQto qto){
        String fileName = "日报周报统计";
        String template = Constants.TEMPLATE_PATH + Constants.REPORT_TEMPLATE_EXCEL_XLSX;
        List<ReportDto> list = reportMapper.queryReportByPage(qto);
        DownloadUtil.downloadXlsx(response, fileName, template, list);
    }

}
