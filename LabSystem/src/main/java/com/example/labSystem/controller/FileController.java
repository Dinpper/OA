package com.example.labSystem.controller;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController extends BaseController {

    // 从配置文件获取上传目录的路径
//    @Value("${file.upload-dir}")
    private String uploadDir = "E:\\file";

    /**
     * 批量上传接口
     *
     * @param request
     * @param response
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/uploadBatch", method = RequestMethod.POST)
    public void uploadBatch(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("files") List<MultipartFile> files) throws Exception {
        // 1. 检查上传的文件是否为空
        if (files.isEmpty()) {
            throw new BusinessException(399, "没有上传文件");
        }
        // 2. 遍历文件列表并保存每个文件
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file);
            }
        }
        // 3. 返回成功结果
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "批量上传成功"));
    }

    // 保存单个文件的方法
    private void saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadDir + File.separator + fileName);
        Files.write(path, file.getBytes());
    }

}
