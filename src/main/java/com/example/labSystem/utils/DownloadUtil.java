package com.example.labSystem.utils;

import cn.idev.excel.FastExcel;
import com.example.labSystem.common.BusinessException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
public class DownloadUtil {


    /**
     * 导出Excel xlsx
     * FastExcel
     * @author Dinpper
     * @since 2025-01-15 10:37:49
     */
    public static <T> void downloadXlsx(HttpServletResponse response, String fileName, String templatePath, List<T> list) {

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");

            // 加载模板文件（从 classpath）
            InputStream templateInputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(templatePath); // 示例：templates/signDurationTemplate.xlsx

            if (templateInputStream == null) {
                log.error("找不到模板文件: {}", templatePath);
                writePlainTextError(response, "模板文件未找到: " + templatePath);
                return;
            }

            // 使用模板生成 Excel 并写入 response 输出流
            try (OutputStream outputStream = response.getOutputStream()) {
                FastExcel.write(outputStream)
                        .withTemplate(templateInputStream)
                        .sheet()
                        .doFill(list);
            }

        } catch (IOException e) {
            log.error("I/O 错误，导出失败", e);
            writePlainTextError(response, "导出失败，I/O 错误");
        } catch (Exception e) {
            log.error("导出失败，发生未知错误", e);
            writePlainTextError(response, "导出失败，未知错误");
        }
    }


    // 错误响应文本输出（避免 response 输出流冲突）
    public static void writePlainTextError(HttpServletResponse response, String message) {
        try {
            // 清空原有 response 设置，避免与 OutputStream 冲突
            response.reset();
            response.setContentType("text/plain;charset=utf-8");
            response.getWriter().write(message);
        } catch (IOException ioException) {
            log.error("写入错误信息失败", ioException);
        }
    }
}
