package com.example.labSystem.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "meeting_rejections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRejection {

    //拒绝会议记录ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //记录创建时间
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //记录更新时间
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    //用户会议ID
    @Column(name = "user_meeting_id", nullable = false)
    private Integer userMeetingId;

    //拒绝会议的原因
    @Column(name = "rejection_reason", length = 255)
    private String rejectionReason;

    //拒绝状态（0：待定，1：接受，2：驳回）
    @Column(name = "rejection_status")
    private Integer rejectionStatus;

    //驳回原因
    @Column(name = "overrule_reason", length = 255)
    private String overruleReason;

    //处理人
    @Column(name = "handled_by", nullable = false, length = 20)
    private String handledBy;
}

