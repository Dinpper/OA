package com.example.labSystem.domain;

import lombok.Data;

@Data
public class Menus {
    private Integer menuId;
    private String menuName;
    private String path;
    private String icon;
    private Integer parentId;
    private String permissionKey;
    private Integer orderNum;
    private Integer isVisible;
}
