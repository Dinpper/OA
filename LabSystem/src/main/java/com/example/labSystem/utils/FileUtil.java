package com.example.labSystem.utils;

import com.example.labSystem.common.BusinessException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Slf4j
public class FileUtil {

    //批量上传接口
    public static void uploadBatch(List<MultipartFile> files, String uploadDir) throws Exception {
        // 创建目标文件夹路径对象
        Path path = Paths.get(uploadDir);

        // 1. 检查上传的文件是否为空
        if (files.isEmpty()) {
            throw new BusinessException(399, "没有上传文件");
        }

        // 使用 Files.createDirectories() 自动创建文件夹（包括父目录）
        Files.createDirectories(path);

        // 2. 遍历文件列表并保存每个文件
        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    saveFile(file, uploadDir);
                }
            }
        } catch (Exception e) {
            throw new BusinessException(399, "上传失败");
        }
    }

    // 保存单个文件的方法
    public static void saveFile(MultipartFile file, String uploadDir) throws IOException {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadDir + File.separator + fileName);
        Files.write(path, file.getBytes());
    }

    public static String getFilesName(List<MultipartFile> files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                fileNames.append(file.getOriginalFilename()).append("; ");
            }
        }
        return fileNames.toString();
    }

    public static String filePathByYMD(String uploadDir){
        // 获取今天的日期
        LocalDate today = LocalDate.now();

        // 获取年、月、日
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        return uploadDir + "\\" + year + "\\" + month + "\\" + day;
    }

    public static void downloadFile(HttpServletResponse response, String filePath, String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName);

        // 确保文件存在
        if (!file.exists()) {
            throw new IOException("File not found: " +  filePath + "\\" + fileName);
        }

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {

            // 设置响应头
            response.setContentType("application/octet-stream");

            // 解决中文文件名乱码问题
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);

            // 设置响应的文件大小
            response.setContentLengthLong(file.length());

            // 将文件写入响应流
            byte[] buffer = new byte[8192]; // 使用更大的缓冲区
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace(); // 输出详细的错误信息
            throw new IOException("Error during file download: " + e.getMessage());
        }
    }
}
