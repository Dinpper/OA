package com.example.labSystem.controller;

import com.example.labSystem.Enum.FileTypeEnum;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.FileByPageDto;
import com.example.labSystem.dto.FileDto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.service.FileService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fileRecord")
@Slf4j
public class FileRecordController extends BaseController {

    @Autowired
    private FileService fileService;

    // 从配置文件获取上传目录的路径
    @Value("${fileStorage.rootDir}")
    private String uploadDir;

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
        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    saveFile(file, uploadDir);
                }
            }
            // 3. 返回成功结果
            BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "批量上传成功"));
        } catch (Exception e) {
            throw new BusinessException(399, "上传失败");
        }
    }

    // 保存单个文件的方法
    public void saveFile(MultipartFile file, String uploadDir) throws IOException {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadDir + File.separator + fileName);
        Files.write(path, file.getBytes());
    }


    @RequestMapping(value = "/getFileType", method = RequestMethod.POST)
    public void uploadBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> list = new ArrayList<>();
        list.add(FileTypeEnum.getTypeNameByCode(1));
        list.add(FileTypeEnum.getTypeNameByCode(2));
        list.add(FileTypeEnum.getTypeNameByCode(3));
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, list));
    }

    @RequestMapping(value = "/queryFileRecordByPage", method = RequestMethod.POST)
    public void queryFileByPage(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryFileByPage,query = {}", GsonUtil.ObjectToJson(qto));
        FileByPageDto result = fileService.queryFileByPage(qto);
        log.info("queryFileByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody FileDto qto) throws Exception {
        fileService.downloadFile(response, qto);
        System.out.println("开始下载文件");
//        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "下载成功"));
    }
}
