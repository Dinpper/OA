package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.domain.Report;
import com.example.labSystem.dto.MeetingsDto;
import com.example.labSystem.mappers.MeetingsMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.service.SparkManagerService;
import com.example.labSystem.utils.EnumReflectionUtil;
import io.github.briqt.spark4j.SparkClient;
import io.github.briqt.spark4j.constant.SparkApiVersion;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.SparkSyncChatResponse;
import io.github.briqt.spark4j.model.request.SparkRequest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Slf4j
public class SparkManagerServiceImpl implements SparkManagerService {
    @Resource
    private SparkClient sparkClient;

    @Autowired
    private MeetingsMapper meetingsMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public void generateMeetingMinutes(Integer meetingId){
        MeetingsDto meetingsDto = meetingsMapper.queryMeetingById(meetingId);
        String content = buildContentFromDto(meetingsDto);
        String meetingMinutes = sendHttpToSpark(MEETINGPRECONDITION, content);
        System.out.println(meetingMinutes);
        Integer result = meetingsMapper.updateSummary(meetingId, meetingMinutes);
        if (result != 1 ) {
            throw new BusinessException(500, "修改总结失败");
        }
    }

    @PostConstruct
    public void init(){
        try {
            // 修改 V1_5 的版本信息
            EnumReflectionUtil.setEnumField(SparkApiVersion.V1_5, "version", "v1.1");
            EnumReflectionUtil.setEnumField(SparkApiVersion.V1_5, "url", "https://spark-api.xf-yun.com/v1.1/chat");
            EnumReflectionUtil.setEnumField(SparkApiVersion.V1_5, "domain", "lite");
        } catch (Exception e) {
            log.error("尝试修改枚举字段异常：", e);
        }
    }

    public static final String MEETINGPRECONDITION = "" +
            "你是一位智能会议纪要生成助手。\n" +
            "请根据用户提供的会议基本信息和会议内容，生成一份完整的会议纪要。\n" +
            "会议纪要需严格按照以下格式编写：\n\n" +
            "【会议基本信息】\n" +
            "会议名称：xxx\n" +
            "会议时间：xxxx年xx月xx日 xx:xx\n" +
            "会议地点：xxx\n" +
            "主持人：xxx\n\n" +
            "【会议议题与内容摘要】\n" +
//            "参会人员：列出实际参会的人员名单（可附上缺席人员）\n" +
            "简要列出本次会议的讨论议题或议程安排\n" +
            "讨论内容/会议内容摘要\n" +
            "按议题或时间顺序，简明扼要地记录各项内容的讨论过程、核心观点、重点信息、结论等\n" +
            "包括提出的问题、建议、争议点、最终决策等\n" +
            "按照上面的模板给出，可以自由补充";

    public String buildContentFromDto(MeetingsDto dto) {
        StringBuilder sb = new StringBuilder();

        // 拼接会议信息
        sb.append("会议主题：").append(dto.getMeetingName()).append("\n");
        sb.append("会议时间：").append(dto.getReportDate());
        if (dto.getStartTime() != null) {
            sb.append(" ").append(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            sb.append(" 至 ").append(dto.getEndTime());
        }
        sb.append("\n");
        sb.append("会议地点：").append(dto.getLocation()).append("\n");
        if (dto.getUserName() != null) {
            sb.append("主持人：").append(dto.getUserName()).append("\n");
        }

        sb.append("\n会议内容：\n");
        sb.append(dto.getDescription());

        return sb.toString();
    }

    public String sendHttpToSpark(String PRECONDITION, final String content){
        // 消息列表，可以再此列表添加历史对话记录
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.systemContent(PRECONDITION));
        // 用户输入内容
        messages.add(SparkMessage.userContent(content));
        // 构造请求
        SparkRequest sparkRequest = SparkRequest.builder()
                // 此时SparkApiVersion.V1_5的内容为修改后的内容
                .apiVersion(SparkApiVersion.V1_5)
                .messages(messages)     // 消息列表
                .build();
        // 同步调用
        SparkSyncChatResponse chatResponse = sparkClient.chatSync(sparkRequest);
        String responseContent = chatResponse.getContent();
//        log.info("spark返回内容：{}",responseContent);
        return responseContent;
    }



    @Override
    public String generatePersonalSummary(String account){
        List<Report> reslist = reportMapper.getReportsByAccountAndMonth(account);
        String content = buildReportFromDto(reslist);
        String result = sendHttpToSpark(PERSONAL_ANALYSIS_TEMPLATE, content);
        return result;
    }

    public static final String PERSONAL_ANALYSIS_TEMPLATE = "" +
            "你是一个实验室的智能个人分析助手。\n" +
            "请根据用户今年的工作周报报内容，生成一份结构清晰的个人年度总结。\n" +
            "总结需严格按照以下格式编写：\n\n" +
            "【个人工作总结】\n" +
            "时间范围：xxxx年xx月xx日 至 xxxx年xx月xx日(时间范围为报告的最开始到最后)\n\n" +
            "一、技术进展\n" +
            "- 简要概述该时间段内在技术学习、技能掌握、问题解决方面的进展\n\n" +
            "二、完成的主要工作\n" +
            "- 以项目或任务为单位，简明扼要地列出完成的主要工作内容\n\n" +
            "三、存在的不足\n" +
            "- 说明当前阶段存在的问题、挑战或未能按计划完成的事项\n\n" +
            "四、建议与后续发展方向\n" +
            "- 提出下阶段工作的建议，包括可学习的新技术、工作改进建议等";

    public String buildReportFromDto(List<Report> reportList) {
        StringBuilder sb = new StringBuilder();

        for (Report report : reportList){
            sb.append("报告提交时间：").append(report.getReportDate()).append("\n");
            sb.append("工作内容：").append(report.getWorkContent()).append("\n");
            sb.append("遇到问题：").append(report.getProblems()).append("\n");
            sb.append("计划：").append(report.getPlan()).append("\n");
        }
        return sb.toString();
    }

}
