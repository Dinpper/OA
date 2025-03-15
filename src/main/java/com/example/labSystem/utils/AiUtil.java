package com.example.labSystem.utils;

import io.github.briqt.spark4j.SparkClient;
import io.github.briqt.spark4j.constant.SparkApiVersion;
import io.github.briqt.spark4j.exception.SparkException;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.SparkSyncChatResponse;
import io.github.briqt.spark4j.model.request.SparkRequest;
import io.github.briqt.spark4j.model.response.SparkTextUsage;

import java.util.ArrayList;
import java.util.List;


public class AiUtil {

    private SparkClient sparkClient;  // 不再是静态字段

    // 构造函数，初始化 sparkClient
    public AiUtil(SparkClient sparkClient) {
        sparkClient.appid = "e4770f78";
        sparkClient.apiKey = "48002f89deec4c40fcddc383b6614df3";
        sparkClient.apiSecret = "NDU1MjZkMGZhY2I1MGZlOWQ3OGU2ZDFh";
        this.sparkClient = sparkClient;
    }

    public String ask(String q) {
        // 设置认证信息
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.systemContent(q));

        SparkRequest sparkRequest = SparkRequest.builder()
                .messages(messages)
                .maxTokens(2048)
                .temperature(0.2)
                .apiVersion(SparkApiVersion.V4_0)
                .build();

        SparkSyncChatResponse chatResponse = null;
        try {
            chatResponse = sparkClient.chatSync(sparkRequest);
            SparkTextUsage textUsage = chatResponse.getTextUsage();

            System.out.println("\n回答：" + chatResponse.getContent());
            System.out.println("\n提问tokens：" + textUsage.getPromptTokens()
                    + "，回答tokens：" + textUsage.getCompletionTokens()
                    + "，总消耗tokens：" + textUsage.getTotalTokens());
        } catch (SparkException e) {
            System.out.println("发生异常了：" + e.getMessage());
        }
        return chatResponse != null ? chatResponse.getContent() : "没有返回内容";
    }
}

