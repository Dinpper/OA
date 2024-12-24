package com.example.labSystem.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * 分组表(Group)实体类
 *
 * @author Dinpper
 * @since 2024-12-17 16:22:05
 */
@Data
public class GroupDto implements Serializable {
    //小组id
    private Integer groupId;
    //组名
    private String groupName;
    //组长名字
    private String groupLeader;
    //删除标识(0:未删除; 1: 已删除)
    private Integer deleteFlag;
    //是否上报标识（0不上报1上报）
    private Integer reportFlag;
}
