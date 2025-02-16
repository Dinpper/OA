package com.example.labSystem;

import cn.idev.excel.FastExcel;
import com.example.labSystem.utils.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootTest
public class file {
    private FileUtil fileUtil;

    public file(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Test
    public void testpdf() {
        File excelFile = new File("C:/Users/Dinpper/Desktop/用户.xlsx");
        File pdfFile = new File("C:/Users/Dinpper/Desktop/用户.pdf");
        String fontPath = "D:/SimSun.ttf";
        int[] sheets = {0};
        FastExcel.convertToPdf(excelFile, pdfFile,null,null);

    }
    @Test
    public void testSaveFile() throws IOException {
        // 1. 准备上传的文件
        MockMultipartFile file = new MockMultipartFile("file", "testfile.txt", "text/plain", "Sample Content".getBytes());

        // 2. 调用 saveFile 方法保存文件
        fileUtil.saveFile(file, "E:\\file");

    }
}
