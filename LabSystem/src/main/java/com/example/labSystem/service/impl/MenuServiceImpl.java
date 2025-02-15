package com.example.labSystem.service.impl;

import com.example.labSystem.domain.Menus;
import com.example.labSystem.mappers.MenusMapper;
import com.example.labSystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenusMapper menusMapper;

    public List<Menus> queryMenusByRole(Integer roleId){
        return menusMapper.queryMenusByRole(roleId);
    }
}
