package com.example.labSystem.controller;

import com.example.labSystem.dto.UserQuestionDto;
import io.github.briqt.spark4j.SparkClient;
import io.github.briqt.spark4j.constant.SparkApiVersion;
import io.github.briqt.spark4j.exception.SparkException;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.SparkSyncChatResponse;
import io.github.briqt.spark4j.model.request.SparkRequest;
import io.github.briqt.spark4j.model.response.SparkTextUsage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @PostMapping("/ask")
    public String question(@RequestBody UserQuestionDto question) {
        SparkClient sparkClient = new SparkClient();

        // 设置认证信息
        sparkClient.appid = "e4770f78";
        sparkClient.apiKey = "48002f89deec4c40fcddc383b6614df3";
        sparkClient.apiSecret = "NDU1MjZkMGZhY2I1MGZlOWQ3OGU2ZDFh";

        // 将数组拼接成字符串，使用空格作为分隔符
        String combinedPrompt = String.join(" ", question.getPrompt());
        String prompt="以下是网络安全实验室的一位学生的标签，请你回答:"+combinedPrompt;
        System.out.println(prompt);
        // 消息列表，可以在此列表添加历史对话记录
        List<SparkMessage> messages = new ArrayList<>();

        messages.add(SparkMessage.systemContent(prompt));
        messages.add(SparkMessage.userContent(question.getQuestion()));
// 构造请求
        SparkRequest sparkRequest = SparkRequest.builder()
// 消息列表
                .messages(messages)
// 模型回答的tokens的最大长度,非必传，默认为2048。
// V1.5取值为[1,4096]
// V2.0取值为[1,8192]
// V3.0取值为[1,8192]
                .maxTokens(2048)
// 核采样阈值。用于决定结果随机性,取值越高随机性越强即相同的问题得到的不同答案的可能性越高 非必传,取值为[0,1],默认为0.5
                .temperature(0.2)
// 指定请求版本，默认使用最新3.0版本
                .apiVersion(SparkApiVersion.V4_0)
                .build();

        SparkSyncChatResponse chatResponse = null;
        try {
            // 同步调用
            chatResponse = sparkClient.chatSync(sparkRequest);
            SparkTextUsage textUsage = chatResponse.getTextUsage();

            System.out.println("\n回答：" + chatResponse.getContent());
            System.out.println("\n提问tokens：" + textUsage.getPromptTokens()
                    + "，回答tokens：" + textUsage.getCompletionTokens()
                    + "，总消耗tokens：" + textUsage.getTotalTokens());
        } catch (SparkException e) {
            System.out.println("发生异常了：" + e.getMessage());
        }
        return chatResponse.getContent();
    }
}

