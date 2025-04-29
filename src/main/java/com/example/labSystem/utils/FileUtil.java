package com.example.labSystem.utils;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.domain.FileRecord;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Slf4j
public class FileUtil {

    public static void createCatalog(String catalog) throws Exception{
        // 创建目标文件夹路径对象
        Path path = Paths.get(catalog);
        // 如果文件夹不存在，则创建目录及其父目录
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);  // 创建目录及其父目录
            }
        } catch (IOException e) {
            throw new BusinessException(399, "创建目标文件夹失败: " + e.getMessage());
        }
    }

    //批量上传接口
    public static void uploadBatch(List<MultipartFile> files, String filePath) throws Exception {
        // 检查上传的文件是否为空
        if (files == null || files.isEmpty()) {
            throw new BusinessException(399, "没有上传文件");
        }

        try {
            // 遍历文件列表并保存每个文件
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    saveFile(file, filePath);
                }
            }
        } catch (Exception e) {
            log.info("上传失败，原因:{}", e.getMessage());
            throw new BusinessException(399, "上传失败");
        }
        log.info("cun chu cheng gong");
    }

    // 保存单个文件的方法
    public static void saveFile(MultipartFile file, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
    }

    public static String generateFilePath(String fileType) {
        // 获取当前年份、月份、日期
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String month = String.format("%02d", Calendar.getInstance().get(Calendar.MONTH) + 1);
        String day = String.format("%02d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        return switch (fileType) {
            case "reports" -> "reports/" + year + "/" + month;
            case "meetings" -> "meetings/" + year + "/" + month;
            case "projects" -> "projects/" + year + "/" + month;
            default -> throw new IllegalArgumentException("Unknown file type");
        };
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

    public static List<String> getFilesNameList(List<MultipartFile> files) {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                list.add(file.getOriginalFilename());
            }
        }
        return list;
    }



    public static void downloadFile(HttpServletResponse response, String filePath, String fileName) throws IOException {
        Path path = Paths.get(filePath).normalize();
        File file = path.toFile();


        // 确保文件存在
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
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

    public static String getFileType(String fileName) {
        // 获取文件扩展名
        String fileExtension = getFileExtension(fileName).toLowerCase();

        // 文件类型映射
        Map<String, String> fileTypeMap = new HashMap<>();
        fileTypeMap.put("pdf", "PDF");
        fileTypeMap.put("doc", "Word");
        fileTypeMap.put("docx", "Word");
        fileTypeMap.put("jpg", "Image");
        fileTypeMap.put("jpeg", "Image");
        fileTypeMap.put("png", "Image");
        fileTypeMap.put("gif", "Image");
        fileTypeMap.put("mp4", "Video");
        fileTypeMap.put("avi", "Video");
        fileTypeMap.put("mov", "Video");
        fileTypeMap.put("mkv", "Video");
        fileTypeMap.put("xlsx", "Excel");
        fileTypeMap.put("xls", "Excel");
        fileTypeMap.put("txt", "Text");
        fileTypeMap.put("zip", "Archive");
        fileTypeMap.put("rar", "Archive");
        fileTypeMap.put("7z", "Archive");
        fileTypeMap.put("md", "Markdown");

        // 如果映射表中存在扩展名，返回对应的文件类型
        if (fileTypeMap.containsKey(fileExtension)) {
            return fileTypeMap.get(fileExtension);
        }

        // 如果没有找到对应的类型，则返回 "Unknown"
        return "Unknown";
    }

    // 获取文件的扩展名
    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return ""; // 如果没有扩展名
        }
        return fileName.substring(dotIndex + 1);
    }

    // 获取文件的字节大小（以字节为单位 B）
    public static long getFileSizeInBytes(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.length();  // 返回文件大小，单位：字节
        }
        return 0;
    }

    // 根据文件字节数返回更加直观的文件大小，自动选择单位（B, KB, MB, GB）
    public static String getFormattedFileSize(String filePath) {
        long bytes = getFileSizeInBytes(filePath);
        return formatFileSize(bytes);
    }

    // 将字节数转换为更直观的单位（如 KB、MB、GB）
    private static String formatFileSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";  // 字节（B）
        } else if (bytes < 1048576) {  // 1024 * 1024
            return String.format("%.2f KB", bytes / 1024.0);  // 千字节（KB）
        } else if (bytes < 1073741824) {  // 1024 * 1024 * 1024
            return String.format("%.2f MB", bytes / 1048576.0);  // 兆字节（MB）
        } else {
            return String.format("%.2f GB", bytes / 1073741824.0);  // 千兆字节（GB）
        }
    }

//    private static FileRecord getFileAttribute(MultipartFile file){
//        String fileName = file.getOriginalFilename();
//        FileRecord fileRecord = new FileRecord();
//        fileRecord.setFileName(fileName);
//        fileRecord.setFileType(getFileType(fileName));
//        return fileRecord;
//    }


    /**
     * 生成用于文件命名的 UUID（无 "-"）
     * @return 唯一字符串
     */
    public static String generateFileName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据原文件名生成唯一文件名，保留扩展名
     * @param originalFilename 原始文件名，例如 "photo.png"
     * @return 生成后的文件名，例如 "9f1b7fa6fbd24f84b7bb6f73a1f623eb.png"
     */
    public static String generateFileNameWithSuffix(String originalFilename) {
        String suffix = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex != -1) {
            suffix = originalFilename.substring(dotIndex); // 包括点
        }
        return generateFileName() + suffix;
    }

    /**
     * 计算文件的 MD5 值
     * @param file 上传的文件
     * @return 文件的 MD5 值（32 位十六进制字符串）
     * @throws Exception 如果计算过程中出现异常
     */
    public static String calculateFileMd5(MultipartFile file) throws Exception {
        // 创建 MessageDigest 对象，使用 MD5 算法
        MessageDigest digest = MessageDigest.getInstance("MD5");

        // 获取文件的输入流
        try (var inputStream = file.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            // 读取文件并计算 MD5 值
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new Exception("Error reading the file", e);
        }

        // 获取最终的 MD5 值并将其转换为 32 位十六进制字符串
        byte[] md5Bytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : md5Bytes) {
            // 将字节转换为 2 位十六进制数
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public static void main(String[] args) {
//        // 示例：获取文件的字节大小和格式化后的文件大小
//        String filePath = "C:\\Users\\Dinpper\\Desktop\\cockpit.md";
//
//        // 返回文件的字节大小
//        long fileSizeInBytes = getFileSizeInBytes(filePath);
//        System.out.println("File size in bytes: " + fileSizeInBytes + " B");
//
//        // 返回文件的格式化大小（单位自动选择）
//        String formattedFileSize = getFormattedFileSize(filePath);
//        System.out.println("Formatted file size: " + formattedFileSize);
//
//        String fileType = getFileType(filePath);
//        System.out.println("file type: " + fileType);meeting

        Path path = Paths.get("E:\\file/meetings/2025/2021212205328/meeting" );
        System.out.println(path);
    }
}
