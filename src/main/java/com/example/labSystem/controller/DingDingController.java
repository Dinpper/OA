package com.example.labSystem.controller;

import com.example.labSystem.service.DingDingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
@Slf4j
public class DingDingController  extends BaseController {

    @Autowired
    private DingDingService dingDingService;

    @GetMapping("/accept")
    public ResponseEntity<String> accept(@RequestParam String user) {
        // TODO: 根据 user 做记录或业务处理
        System.out.println(user + " 确认参加会议");
        return ResponseEntity.ok("<h3>✅ 您已成功确认参会</h3>");
    }

    @GetMapping("/reject")
    public ResponseEntity<String> reject(@RequestParam String user) {
        System.out.println(user + " 拒绝参加会议");
        return ResponseEntity.ok("<h3>❌ 您已拒绝参加会议！</h3>");
    }

    @GetMapping("/suggest")
    public ResponseEntity<String> suggest(@RequestParam String user) {
        System.out.println(user + " 建议调整会议");
        return ResponseEntity.ok("<h3>📝 您已提交调整建议！</h3>");
    }
}
