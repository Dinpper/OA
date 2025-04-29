package com.example.labSystem.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "email_group_mapping")
public class EmailGroupMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "inputDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inputDate;

    @Column(name = "updateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(length = 20)
    private String account;

    private String userName;

    @Column(length = 45)
    private String email;

    @Column(name = "groupId", nullable = false)
    private Integer groupId;
}