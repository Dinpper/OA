package com.example.labSystem.dto;

import lombok.Data;

/**
 * 分组表(Groups)实体类
 *
 * @author Dinpper
 * @since 2024-12-17 16:22:05
 */
@Data
public class GroupDto{
    //小组id
    private Integer groupId;
    //组名
    private String groupName;
    //组长账号
    private String groupLeader;
    //组长名字
    private String leaderName;
    //组员人数
    private Integer memberCount;
    //删除标识(0:未删除; 1: 已删除)
    private Integer deleteFlag;
    //是否上报标识（0不上报1上报）
    private Integer reportFlag;
}
