package com.example.labSystem.utils;

import cn.idev.excel.FastExcel;
import com.example.labSystem.common.BusinessException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
public class ExcelUtil {


    /**
     * 导出Excel xlsx
     * FastExcel
     * @author Dinpper
     * @since 2025-01-15 10:37:49
     */
    public static <T> void downloadXlsx(HttpServletResponse response, String fileName, String template, List<T> list) throws Exception {
        // 设置文件类型为 Excel 文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 设置字符编码为 UTF-8，确保文件名和内容中的字符能正确显示
        response.setCharacterEncoding("utf-8");
        // 设置文件名并进行 URL 编码
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        // 设置下载文件的响应头，指定文件名和编码格式
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");

        // 使用 FastExcel 库生成 Excel 文件并写入到输出流
        try (OutputStream outputStream = response.getOutputStream()) {
            FastExcel.write(outputStream)  // 将输出流传入
                    .withTemplate(template)   // 使用模板
                    .sheet()                  // 默认一个 sheet
                    .doFill(list);            // 填充数据
        } catch (FileNotFoundException e) {
            log.error("模板文件未找到: " + template, e);
            throw new BusinessException(500, "模板文件未找到");
        } catch (IOException e) {
            log.error("I/O 错误，导出失败", e);
            throw new BusinessException(500, "导出失败，I/O 错误");
        } catch (Exception e) {
            log.error("导出失败，发生未知错误", e);
            throw new BusinessException(500, "导出失败，未知错误");
        }
    }
}
