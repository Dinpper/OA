package com.example.labSystem.dto;

import lombok.Data;

@Data
public class DingCallbackReq {
    private String meetingId;
    private String userId;
    private String reason; // 建议调整 or 拒绝理由（可选）
}

