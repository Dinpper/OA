package com.example.labSystem.service.impl;

import com.example.labSystem.dto.DingMsgCard;
import com.example.labSystem.dto.MeetingsDto;
import com.example.labSystem.mappers.MeetingsMapper;
import com.example.labSystem.mappers.UserMeetingsMapper;
import com.example.labSystem.service.DingDingService;
import com.example.labSystem.utils.DingDingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
@Slf4j
public class DingDingServiceImpl implements DingDingService {

    @Value("${dingtalk.baseUrl}")
    private String baseUrl;

    @Value("${dingtalk.secret}")
    private String secret;

    @Value("${serverBaseUrl}")
    private String serverBaseUrl;

    @Autowired
    private MeetingsMapper meetingsMapper;

    @Autowired
    private UserMeetingsMapper userMeetingsMapper;

    @Override
    public void sendMeetingMessage(Integer meetingId) throws Exception {

        MeetingsDto meetingsDto = meetingsMapper.queryMeetingById(meetingId);
        List<String> atMobiles = userMeetingsMapper.queryMeetingMemberPhone(meetingId);

        DingMsgCard card = buildMeetingCard(meetingsDto, atMobiles, serverBaseUrl);
        sendDingCard(card, baseUrl, atMobiles);
    }

    /**
     * 主方法：发送 DingMsgCard 卡片消息
     */
    public void sendDingCard(DingMsgCard card, String baseUrl, List<String> atMobiles) {
        try {
            String fullUrl = DingDingUtil.getFullUrl(baseUrl);

            Map<String, Object> payload = new HashMap<>();
            payload.put("msgtype", card.getMsgtype());
            payload.put("actionCard", card.getActionCard());

            Map<String, Object> at = new HashMap<>();
            at.put("atMobiles", atMobiles);
            at.put("isAtAll", false);
            payload.put("at", at);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(fullUrl, request, String.class);

            System.out.println("钉钉响应：" + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("钉钉消息发送失败: " + e.getMessage());
        }
    }


    public DingMsgCard buildMeetingCard(MeetingsDto meeting, List<String> atMobiles, String serverBaseUrl) {
        String formattedTime = meeting.getReportDate() + " " + meeting.getStartTime();

        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("📍 地点：").append(meeting.getLocation()).append("\n\n")
                .append("🕒 时间：").append(formattedTime).append("\n\n")
                .append("📌 发起人：").append(
                        Optional.ofNullable(meeting.getOrganizerName()).orElse(meeting.getUserName())
                ).append("\n\n")
                .append("📝 会议议程：\n\n")
                .append(meeting.getDescription());

        if (atMobiles != null && !atMobiles.isEmpty()) {
            textBuilder.append("\n\n👥 参会成员：")
                    .append(atMobiles.stream().map(m -> "@" + m).collect(Collectors.joining(" ")));
        }

        String userParam = URLEncoder.encode(atMobiles.get(0), StandardCharsets.UTF_8);

        String acceptUrl = serverBaseUrl + "/callback/accept?user=" + userParam;
        String rejectUrl = serverBaseUrl + "/callback/reject?user=" + userParam;
        String suggestUrl = serverBaseUrl + "/callback/suggest?user=" + userParam;

        return new DingMsgCard.Builder()
                .title("📅 会议邀请：" + meeting.getMeetingName())
                .text(textBuilder.toString())
                .addActionButton("accept", "✅ 确认参加", "primary", acceptUrl)
                .addActionButton("reject", "❌ 拒绝", "danger", rejectUrl)
                .addActionButton("suggest", "📝 建议调整", "default", suggestUrl)
                .build();
    }




}
