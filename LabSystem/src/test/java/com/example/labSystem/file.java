package com.example.labSystem;

import cn.idev.excel.FastExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
@Slf4j
public class file {

    @Test
    public void testpdf() {
        File excelFile = new File("C:/Users/Dinpper/Desktop/用户.xlsx");
        File pdfFile = new File("C:/Users/Dinpper/Desktop/用户.pdf");
        String fontPath = "D:/SimSun.ttf";
        int[] sheets = {0};
        FastExcel.convertToPdf(excelFile, pdfFile,null,null);

    }
}
