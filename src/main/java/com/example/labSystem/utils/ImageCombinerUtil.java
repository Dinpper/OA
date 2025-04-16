package com.example.labSystem.utils;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.example.labSystem.domain.Report;
import com.example.labSystem.dto.PostElementsDto;
import com.example.labSystem.mappers.RecordMapper;
import com.example.labSystem.mappers.ReportMapper;
import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.element.TextElement;
import com.freewayso.image.combiner.enums.BaseLine;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

@Component
public class ImageCombinerUtil {

    @Value("${fileStorage.imageDir}")
    private String imagePath;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ReportMapper reportMapper;

    public int getReportZiShu(String account){
        List<Report> reports = reportMapper.getReportYearRecords(account);
        int totalWords = 0;

        for (Report report : reports) {
            if (report.getWorkContent() != null) {
                totalWords += report.getWorkContent().length();  // 统计 workContent 字符数
            }
            if (report.getPlan() != null) {
                totalWords += report.getPlan().length();  // 统计 plan 字符数
            }
        }
        return totalWords;
    }

    public void generatePost(PostElementsDto postElements) throws Exception {
        String bgImageUrl = "https://img.tukuppt.com/ad_preview/00/03/32/5c98a5df6f708.jpg!/fw/980";                       //背景图（测试url形式）
//        BufferedImage avatar = ImgUtil.read("/main/resources/images/avatar.jpg");
        BufferedImage avatar = ImgUtil.read(ResourceUtil.getStream("images/avatar.jpg"));
        String username = "@"+postElements.getUsername();
        String str1="本学期你共签到了";
        Double signTime = recordMapper.querySignDurationYearTotal(postElements.getAccount());
        String str2= String.valueOf(signTime);
        String str3="个小时";
        String str4="提交了";
        Integer submitCount = reportMapper.queryReportSubmitCountYear(postElements.getAccount());
        String str5= String.valueOf(submitCount);
        String str6="次周报";
        String str7="总计";
        String str8= String.valueOf(getReportZiShu(postElements.getAccount()));
        String str9="个字";
        String str10="超越了实验室";
        String str11="12";
        String str12="%的人!";
        String str13="你还需要再加把劲!";
        //keywords
        String kw1= postElements.getKw1();
        String kw2= postElements.getKw2();
        String kw3= postElements.getKw3();
        String kw4= postElements.getKw4();

        //一些常量
        final int X=66;
        int fontsize=40;
        int xxxFontsize=60;
        int kwFontSize=50;
        int offsetX=20;
        int offsetY=40;
        int myX=X;
        int myY=480;
        String xxxFont="WenQuanYi Zen Hei";
        String font="Noto Sans CJK SC";
        //合成器和背景图（整个图片的宽高和相关计算依赖于背景图，所以背景图的大小是个基准）
        ImageCombiner combiner = new ImageCombiner(bgImageUrl, OutputFormat.JPG);

        //标题（默认字体为“阿里巴巴普惠体”，也可以自己指定字体名称或Font对象）
        //str1的内容
        TextElement textElement1 = combiner.addTextElement(str1, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47,79,79);
        myX+= textElement1.getWidth()+offsetX;

        TextElement textElement2 = combiner.addTextElement(str2, xxxFontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(25, 25, 112);
        myX+= textElement2.getWidth()+offsetX;

        TextElement textElement3 = combiner.addTextElement(str3, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47, 79, 79);
        myY+=textElement3.getHeight()+offsetY;

        myX=X;
        TextElement textElement4 = combiner.addTextElement(str4, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47, 79, 79);
        myX+= textElement4.getWidth()+offsetX;

        TextElement textElement5 = combiner.addTextElement(str5, xxxFontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(25, 25, 112);
        myX+= textElement5.getWidth()+offsetX;

        TextElement textElement6 = combiner.addTextElement(str6, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47, 79, 79);
        myY+=textElement6.getHeight()+offsetY;

        myX=X;
        TextElement textElement7 = combiner.addTextElement(str7, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47, 79, 79);
        myX+= textElement7.getWidth()+offsetX;

        TextElement textElement8 = combiner.addTextElement(str8, xxxFontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(25, 25, 112);
        myX+= textElement8.getWidth()+offsetX;

        //个字
        TextElement textElement9 = combiner.addTextElement(str9, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47, 79, 79);
        myY+=textElement9.getHeight()+offsetY;

        myX=X;
        TextElement textElement10 = combiner.addTextElement(str10, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47, 79, 79);
        myX+= textElement10.getWidth()+offsetX;

        TextElement textElement11 = combiner.addTextElement(str11, xxxFontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(25, 25, 112);
        myX+=textElement11.getHeight()+offsetX;


        TextElement textElement12 = combiner.addTextElement(str12, xxxFont,fontsize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(47, 79, 79);
        myY+=textElement12.getHeight()+offsetY;

        myX=X;
        TextElement textElement13 = combiner.addTextElement(str13, xxxFont,xxxFontsize, myX, myY+20)
                .setBaseLine(BaseLine.Bottom)
                .setColor(Color.black);

        combiner.addTextElement("你的2024学习时刻关键词",xxxFont,40,X,1000)
                .setBaseLine(BaseLine.Bottom)
                .setColor(54,100,139);

        myY=1050;
        //加文本元素
        TextElement textElement14 = combiner.addTextElement(kw1, font,Font.BOLD,kwFontSize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(28,28,28);
        myY+=textElement14.getHeight()+15;

        TextElement textElement15 = combiner.addTextElement(kw2,font,Font.BOLD, kwFontSize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(28,28,28);
        myY+=textElement15.getHeight()+15;

        TextElement textElement16 = combiner.addTextElement(kw3, font,Font.BOLD,kwFontSize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(28,28,28);
        myY+=textElement16.getHeight()+15;

        TextElement textElement17 = combiner.addTextElement(kw4,font,Font.BOLD, kwFontSize, myX, myY)
                .setBaseLine(BaseLine.Bottom)
                .setColor(28,28,28);


        //用户名
        combiner.addTextElement(username,"WenQuanYi Zen Hei Mono",40,66,350);

        //头像（圆角设置一定的大小，可以把头像变成圆的）
        combiner.addImageElement(avatar, 66, 200, 130, 130, ZoomMode.WidthHeight)
                .setRoundCorner(400);

        //执行图片合并
        combiner.combine();

        //保存文件
        combiner.save("/home/portrait/post/post_"
                + DigestUtils.md5DigestAsHex(postElements.getAccount().getBytes())+".png");
    }


}
