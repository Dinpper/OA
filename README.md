# LabSystem - 实验室管理系统

此项目为后端部分，前端项目: https://github.com/Dinpper/LabSystem-web

## 打jar包

```
mvn clean package -DskipTests=true -Dspring.profiles.active=prod -P release 
```

## 生产环境部署

```
/www/server/java/jdk-17.0.8/bin/java -Duser.timezone=Asia/Shanghai -Xmx1024M -Xms256M -jar /www/wwwroot/labsystem/backend/LabSystem-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod --server.port=8880
```



## 功能描述

包括签到签退、日报周报、会议管理、请假管理、文件管理、组织管理、个人分析、邮件提醒等功能

### 签到签退

- 首页进行签到签退，有图表和报表的形式显示

### 报告管理

- 用于提交日报周报，可以添加多个附件上传

### 会议管理

- 用于发布会议，可通过手机号来进行@某人
- 接入钉钉机器人dingtalk，详细链接https://open.dingtalk.com/document/orgapp/robot-overview
- 详细实现见 DingDingService，在application里面配置dingtalk的baseUrl、secret

### 请假管理

- 用户提交请假申请之后，管理员可以对该申请进行处理

### 文件管理

- 用于文件的统一管理，可以下载
- 文件来源有 日报周报、会议分享、项目文件

### 组织管理

- 人员管理：对于用户的个人信息修改，上报条件修改，角色权限修改
- 小组管理：小组的添加和删除

### 邮件配置

- 配置报告发送方式：不发生邮件、日报、周报
- 节假日设置：跳过节假日、不跳过节假日
- 内容配置：邮件发送会按照上面的人员信息进行自动发送，可以选择每个人的邮件内容是由哪些组来组成

### 个人分析

- 通过星火大模型Spark来进行个人分析总结
- 具体实现见 SparkManagerServiceImpl

### 个人设置

- 显示个人信息
- 一年的热力图尚未实现



### 定时任务

存放在src/main/java/com/example/labSystem/scheduler文件中

1. ReportTask
   1. 晚上11点，自动发送日报或者周报，日报周报内容为本日或本周的签到时长+提交的报告内容
   2. 可以自定义是否跳过节假日、选择以周报或者日报的形式发送，在邮件配置界面可以选择
2. ScheduledService
   1. 每月25日获取节日信息。存储在holiday_date表中
   2. 如果数据库没有初始化，自动初始化数据库，生成当年的节假日信息
3. SignOutReminderScheduler
   1. 签退提醒，没5分钟扫描一次数据库
4. SignOutScheduler
   1. 自动签退，每天晚上 23:55 触发，自动签退时间为23:59:59



### 权限管理

- 只是简单的分了三种角色，在前端处理，按照不同的角色显示不同的内容
