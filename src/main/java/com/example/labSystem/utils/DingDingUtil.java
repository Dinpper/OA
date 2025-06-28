package com.example.labSystem.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class DingDingUtil {

    public static void send(String url, String msg) {
        try {
            //创建钉钉客户端
            DingTalkClient client = new DefaultDingTalkClient(url);
            //构建自定义机器人请求
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            //设置固定为文字版信息类型
            request.setMsgtype("text");
            //构建自定义机器人文字类型请求
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            //调用封装文本信息。
            text.setContent(msg);
            request.setText(text);
            //构建自定义机器人@人范围请求
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            //明确为 true。通知时@所有人
            at.setIsAtAll(true);
            request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            log.info("发送完成，返回值：{}", response.getBody());
        } catch (ApiException e) {
            log.error("钉钉接口调用异常：", e);
        }
    }

    public static boolean checkSignature(String sign, String timestamp) {
        String secret = "SECd4feed8c60b3caecde0dd8b5a2f6cf7a12f39a9d3f4c025875998f9163176246";
        try {
            String content = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
            String expectedSign = URLEncoder.encode(Base64.getEncoder().encodeToString(signData), "UTF-8");
            return expectedSign.equals(sign);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getSign(Long timestamp) throws Exception {
        String secret = "SECd4feed8c60b3caecde0dd8b5a2f6cf7a12f39a9d3f4c025875998f9163176246";

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = URLEncoder.encode(new String(org.apache.commons.codec.binary.Base64.encodeBase64(signData)),"UTF-8");
        return sign;
    }

    public static String getFullUrl(String baseUrl) throws Exception {
        Long timestamp = System.currentTimeMillis();
        String sign = DingDingUtil.getSign(timestamp);
        String fullUrl = String.format(baseUrl + "&timestamp=%s&sign=%s", timestamp, sign);
        return fullUrl;
    }
}

