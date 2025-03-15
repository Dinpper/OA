package com.example.labSystem.service;

import com.example.labSystem.dto.*;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface GroupService {

    List<String> queryGroupsList();

    GroupByPageDto queryGroupsByPage(PageRequestQto qto);

    void download(HttpServletResponse response, PageRequestQto qto) throws Exception;

    void updateGroup(GroupDto qto);

    void deleteGroup(String groupName) throws Exception;

    void addGroup(CommonRequestQto qto);

}
