package com.example.labSystem.service;

import com.example.labSystem.domain.Menus;
import java.util.List;

public interface MenuService {
    List<Menus> queryMenusByRole(Integer roleId);
}
