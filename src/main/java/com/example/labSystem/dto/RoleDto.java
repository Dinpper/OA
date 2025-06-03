package com.example.labSystem.dto;
import lombok.Data;
import java.util.Date;

@Data
public class RoleDto {
    private Integer roleId;
    private Date inputDate;
    private Date updateDate;
    private String roleName;
    private String description;
}
