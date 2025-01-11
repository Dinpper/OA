package com.example.labSystem.service.impl;


import cn.idev.excel.FastExcel;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.RecordExcelDto;
import com.example.labSystem.dto.RecordSonDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.service.SignDurationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
@Slf4j
public class SignDurationServiceImpl implements SignDurationService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public RecordDto queryTodayByUser(CommonRequestQto qto) {
        RecordDto dto = new RecordDto();
        Integer signDuration = recordMapper.querySignDurationToDayByUser(qto);
        signDuration = signDuration == null ? 0 :signDuration;
        dto.setSignDuration(signDuration);
        return dto;
    }

    @Override
    public RecordDto queryWeek(CommonRequestQto qto) {
        RecordDto dto = new RecordDto();
        List<RecordSonDto> list = recordMapper.querySignDurationWeek(qto);
        dto.setWeekList(list);
        return dto;
    }

    @Override
    public void download(HttpServletResponse response, CommonRequestQto qto) throws Exception {
        // 设置文件类型为 Excel 文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 设置字符编码为 UTF-8，确保文件名和内容中的字符能正确显示
        response.setCharacterEncoding("utf-8");
        // 设置文件名并进行 URL 编码
        String fileName = URLEncoder.encode("签到", "UTF-8").replaceAll("\\+", "%20");
        // 设置下载文件的响应头，指定文件名和编码格式
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 获取数据
        List<RecordExcelDto> list = recordMapper.queryRecordByPage(qto);
        String template = "src/main/resources/templates/签到时长模板.xlsx";

        // 使用 FastExcel 库生成 Excel 文件并写入到输出流
        try (OutputStream outputStream = response.getOutputStream()) {
            FastExcel.write(outputStream)  // 将输出流传入
                    .withTemplate(template)   // 使用模板
                    .sheet()                  // 默认一个 sheet
                    .doFill(list);            // 填充数据
        } catch (Exception e) {
            // 处理流操作异常
            log.info("downLoadByUser, 导出失败，流操作异常：", e);
            throw new BusinessException(500, "导出失败");
        }
    }

}
