-- labsystem.achievement definition

CREATE TABLE `achievement` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '报告编号',
                               `fileName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `reportDate` date DEFAULT NULL COMMENT '统计日期',
                               `account` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `deleteFlag` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识(0:未删除; 1: 已删除)',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.award definition

CREATE TABLE `award` (
                         `prize_id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                         `competition_name` varchar(100) NOT NULL COMMENT '竞赛名称',
                         `works_name` varchar(100) DEFAULT NULL COMMENT '作品名称',
                         `award_rank` varchar(50) DEFAULT NULL COMMENT '获奖等级（如：一等奖）',
                         `member` text COMMENT '参赛队员（用逗号分隔）',
                         `tutor` varchar(100) DEFAULT NULL COMMENT '指导老师',
                         `award_time` date DEFAULT NULL COMMENT '获奖日期',
                         `sub_user` varchar(50) DEFAULT NULL COMMENT '上传用户账号或ID',
                         `sub_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
                         `award_desc` text COMMENT '备注/说明',
                         `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
                         PRIMARY KEY (`prize_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='竞赛获奖记录表';


-- labsystem.course definition

CREATE TABLE `course` (
                          `courseId` int NOT NULL AUTO_INCREMENT,
                          `inputDate` datetime DEFAULT NULL COMMENT '插入时间',
                          `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
                          `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户账号',
                          `courseFirst` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseSecond` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseThird` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseFourth` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseFifth` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseSixth` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseSeventh` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseEighth` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseNinth` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseTenth` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseEleventh` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `courseTwelfth` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `date` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学年/学期',
                          `week` int DEFAULT NULL COMMENT '星期',
                          PRIMARY KEY (`courseId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2063 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.coursetime definition

CREATE TABLE `coursetime` (
                              `coursetimeId` int NOT NULL AUTO_INCREMENT,
                              `inputDate` datetime DEFAULT NULL COMMENT '插入时间',
                              `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
                              `date` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                              `firstStart` time DEFAULT NULL,
                              `secondStart` time DEFAULT NULL,
                              `thirdStart` time DEFAULT NULL,
                              `fourthStart` time DEFAULT NULL,
                              `fifthStart` time DEFAULT NULL,
                              `sixthStart` time DEFAULT NULL,
                              `seventhStart` time DEFAULT NULL,
                              `eighthStart` time DEFAULT NULL,
                              `ninthStart` time DEFAULT NULL,
                              `tenthStart` time DEFAULT NULL,
                              `eleventhStart` time DEFAULT NULL,
                              `twelfthStart` time DEFAULT NULL,
                              `duration` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                              PRIMARY KEY (`coursetimeId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.email_group_mapping definition

CREATE TABLE `email_group_mapping` (
                                       `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                       `inputDate` datetime DEFAULT NULL COMMENT '插入时间',
                                       `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
                                       `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户账号',
                                       `userName` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '成员姓名',
                                       `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱(不是系统用户但是需要接受邮件)',
                                       `groupId` int NOT NULL COMMENT '小组ID',
                                       `deleteFlag` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识(0:未删除; 1: 已删除)',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.file_record definition

CREATE TABLE `file_record` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '文件ID',
                               `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文件名称',
                               `file_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文件存储路径',
                               `file_size` bigint DEFAULT NULL COMMENT '文件大小（单位：字节）',
                               `uploaded_by` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '上传者ID',
                               `uploaded_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
                               `file_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件类型（如：PDF，Word，图片等）',
                               `visibility` tinyint NOT NULL DEFAULT '1' COMMENT '文件可见性（1：公开，2：私有）',
                               `source_type` tinyint NOT NULL COMMENT '文件来源类型（1：日报周报，2：会议共享，3：项目文件）',
                               `related_id` int DEFAULT NULL COMMENT '关联ID，依据source_type，指向相关表的记录ID',
                               `stored_file_name` varchar(255) NOT NULL COMMENT '实际存储的UUID命名文件名',
                               `file_md5` char(32) DEFAULT NULL COMMENT '文件内容的MD5值',
                               `download_count` int DEFAULT '0' COMMENT '文件下载次数',
                               `description` text COMMENT '文件描述',
                               `deleted` tinyint(1) DEFAULT '0' COMMENT '是否已删除（0：否，1：是）',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.`groups` definition

CREATE TABLE `groups` (
                          `groupId` int NOT NULL AUTO_INCREMENT COMMENT '小组id',
                          `inputDate` datetime DEFAULT NULL COMMENT '插入时间',
                          `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
                          `groupName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '组名',
                          `leaderAccount` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '组长名字',
                          `deleteFlag` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识(0:未删除; 1: 已删除)',
                          `reportFlag` tinyint DEFAULT '0' COMMENT '是否上报标识（0不上报1上报）',
                          PRIMARY KEY (`groupId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.harvest definition

CREATE TABLE `harvest` (
                           `harvestId` int NOT NULL AUTO_INCREMENT,
                           `reportDate` date DEFAULT NULL COMMENT '上报时间',
                           `fileName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文件名称',
                           `filePath` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文件路径',
                           `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账号',
                           PRIMARY KEY (`harvestId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.holiday_date definition

CREATE TABLE `holiday_date` (
                                `date` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '日期yyyy-MM-dd',
                                `year` int NOT NULL,
                                `month` int NOT NULL,
                                `day` int NOT NULL,
                                `status` int DEFAULT '0' COMMENT '0普通工作日1周末2需要补班的工作日3法定节假日',
                                PRIMARY KEY (`date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- labsystem.`leave` definition

CREATE TABLE `leave` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                         `created_at` datetime DEFAULT NULL COMMENT '创建时间',
                         `reportDate` date DEFAULT NULL COMMENT '上报时间',
                         `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '请假人账号',
                         `startDate` datetime NOT NULL COMMENT '请假开始时间',
                         `endDate` datetime NOT NULL COMMENT '请假结束时间',
                         `reason` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '请假原因',
                         `allowedFlag` tinyint NOT NULL DEFAULT '0' COMMENT '0-待审核，1-批准，2-不批准',
                         `handlers` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '处理人',
                         `remarks` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '拒绝原因',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.meeting_rejections definition

CREATE TABLE `meeting_rejections` (
                                      `id` int NOT NULL AUTO_INCREMENT COMMENT '拒绝会议记录ID',
                                      `created_at` datetime NOT NULL COMMENT '记录创建时间',
                                      `updated_at` datetime DEFAULT NULL COMMENT '记录更新时间',
                                      `user_meeting_id` int NOT NULL COMMENT '用户会议ID',
                                      `rejection_reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '拒绝会议的原因',
                                      `rejection_status` tinyint DEFAULT NULL COMMENT '拒绝状态（0：待定，1：接受，2：驳回）',
                                      `overrule_reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '驳回原因',
                                      `handled_by` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '处理人',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.meetings definition

CREATE TABLE `meetings` (
                            `meetingId` int NOT NULL AUTO_INCREMENT COMMENT '会议ID',
                            `inputDate` datetime DEFAULT NULL COMMENT '生成时间',
                            `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
                            `meetingName` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '会议名称',
                            `reportDate` date DEFAULT NULL COMMENT '会议日期',
                            `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '会议描述',
                            `startTime` time DEFAULT NULL COMMENT '会议开始时间',
                            `endTime` time DEFAULT NULL COMMENT '会议结束时间',
                            `location` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '会议地点',
                            `organizerAccount` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '主办方用户账号',
                            `keyword` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关键字',
                            `summary` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '会议总结',
                            PRIMARY KEY (`meetingId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.menus definition

CREATE TABLE `menus` (
                         `menuId` int NOT NULL AUTO_INCREMENT,
                         `menuName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
                         `path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '前端路由路径',
                         `icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单图标',
                         `parentId` int DEFAULT NULL COMMENT '父级菜单ID（用于层级结构）',
                         `permissionKey` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联权限标识（与permissions表对应）',
                         `orderNum` int DEFAULT '0' COMMENT '排序',
                         `isVisible` tinyint DEFAULT '1' COMMENT '是否可见（0: 隐藏, 1: 显示）',
                         PRIMARY KEY (`menuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.permissions definition

CREATE TABLE `permissions` (
                               `permissionId` int NOT NULL AUTO_INCREMENT,
                               `inputDate` datetime DEFAULT NULL COMMENT '插入时间',
                               `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
                               `permissionName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '权限名称',
                               `permissionKey` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '权限标识（view_user, edit_user 等）',
                               `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '权限描述',
                               PRIMARY KEY (`permissionId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.record definition

CREATE TABLE `record` (
                          `recordId` int NOT NULL AUTO_INCREMENT COMMENT '记录编号',
                          `startDate` datetime NOT NULL COMMENT '签到时间',
                          `endDate` datetime DEFAULT NULL COMMENT '签退时间',
                          `reportDate` date DEFAULT NULL COMMENT '统计日期',
                          `signDuration` int DEFAULT NULL COMMENT '签到时长',
                          `statusType` tinyint DEFAULT NULL COMMENT '签到状态（0未签退1已签退）',
                          `isReminded` tinyint DEFAULT '0' COMMENT '被提醒状态（0未提醒1已提醒）',
                          `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户Code',
                          PRIMARY KEY (`recordId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50260 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.report definition

CREATE TABLE `report` (
                          `reportId` int NOT NULL AUTO_INCREMENT COMMENT '报告编号',
                          `inputDate` datetime NOT NULL COMMENT '生成时间',
                          `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
                          `reportDate` date DEFAULT NULL COMMENT '统计日期',
                          `workContent` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '工作内容',
                          `problems` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '遇到问题',
                          `plan` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '计划',
                          `achievement` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '附件路径集合',
                          `submittedFlag` tinyint DEFAULT NULL COMMENT '提交状态（0草稿1提交）',
                          `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账号',
                          PRIMARY KEY (`reportId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11325 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.rolemenus definition

CREATE TABLE `rolemenus` (
                             `roleMenuId` int NOT NULL AUTO_INCREMENT,
                             `roleId` int NOT NULL COMMENT '角色ID',
                             `menuId` int NOT NULL COMMENT '菜单ID',
                             PRIMARY KEY (`roleMenuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.rolepermissions definition

CREATE TABLE `rolepermissions` (
                                   `rolePermissionsId` int NOT NULL AUTO_INCREMENT,
                                   `roleId` int NOT NULL COMMENT '角色ID',
                                   `permissionId` int NOT NULL COMMENT '权限ID',
                                   PRIMARY KEY (`rolePermissionsId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.roles definition

CREATE TABLE `roles` (
                         `roleId` int NOT NULL AUTO_INCREMENT,
                         `inputDate` datetime DEFAULT NULL COMMENT '插入时间',
                         `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
                         `roleName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
                         `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色描述',
                         PRIMARY KEY (`roleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.schedule definition

CREATE TABLE `schedule` (
                            `scheduleId` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `account` int NOT NULL COMMENT '用户账号',
                            `courseName` int NOT NULL COMMENT '课程名称',
                            `weekDay` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '周几',
                            `startTime` time NOT NULL COMMENT '开始时间',
                            `endTime` time NOT NULL COMMENT '结束时间',
                            PRIMARY KEY (`scheduleId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.system_config definition

CREATE TABLE `system_config` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '配置项ID',
                                 `config_key` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '配置项的键（唯一标识）',
                                 `config_value` text CHARACTER SET utf8mb4  NOT NULL COMMENT '配置项的值',
                                 `description` varchar(255) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '配置项描述',
                                 `status` tinyint(1) DEFAULT '1' COMMENT '状态: 0=禁用, 1=启用',
                                 `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE KEY `config_key` (`config_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4  ROW_FORMAT=DYNAMIC COMMENT='系统配置表';


-- labsystem.usermeetings definition

CREATE TABLE `usermeetings` (
                                `userMeetingsId` int NOT NULL AUTO_INCREMENT COMMENT '记录ID',
                                `inputDate` datetime NOT NULL COMMENT '生成时间',
                                `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
                                `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户账号',
                                `meetingId` int NOT NULL COMMENT '会议ID',
                                `status` tinyint DEFAULT NULL COMMENT '状态（0：待定，1：接受，2：拒绝）',
                                `reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '拒绝原因',
                                `notifiedFlag` tinyint DEFAULT '0' COMMENT '是否已通知（0：未通知，1：已通知）',
                                `checkInTime` datetime DEFAULT NULL COMMENT '签到时间',
                                `checkOutTime` datetime DEFAULT NULL COMMENT '签退时间',
                                `summary` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '会议个人总结',
                                PRIMARY KEY (`userMeetingsId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.userroles definition

CREATE TABLE `userroles` (
                             `userRolesId` int NOT NULL AUTO_INCREMENT,
                             `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户账号',
                             `roleId` int DEFAULT NULL COMMENT '角色ID',
                             PRIMARY KEY (`userRolesId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=284 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;


-- labsystem.users definition

CREATE TABLE `users` (
                         `userId` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                         `inputDate` datetime DEFAULT NULL COMMENT '创建时间',
                         `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
                         `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账号',
                         `password` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
                         `userName` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '成员姓名',
                         `sex` tinyint NOT NULL DEFAULT '0' COMMENT '0-男，1-女',
                         `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电话',
                         `grade` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属年级',
                         `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
                         `groupName` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属小组',
                         `deleteFlag` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识(0:未删除; 1: 已删除)',
                         `reportFlag` tinyint NOT NULL COMMENT '是否上报标识（0不上报1上报）',
                         `stuNumber` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学号',
                         `className` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '班级',
                         PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;