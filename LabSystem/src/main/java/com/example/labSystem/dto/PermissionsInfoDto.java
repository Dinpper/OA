package com.example.labSystem.dto;

import com.example.labSystem.domain.Menus;
import com.example.labSystem.domain.Permissions;
import lombok.Data;

import java.util.List;

@Data
public class PermissionsInfoDto {
    private UserDto user;
    private List<String> permissions;
    private List<Menus> menus;
}