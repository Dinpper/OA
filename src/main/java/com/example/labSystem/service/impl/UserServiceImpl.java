package com.example.labSystem.service.impl;

import cn.idev.excel.FastExcel;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.GroupMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private GroupMapper groupMapper;


    @Override
    public UserDto queryUserMessage(CommonRequestQto qto) {
        String account = qto.getAccount();
        UserDto dto = usersMapper.queryUserMessage(account);
        return dto;
    }

    @Override
    public List<GroupUserDto> queryGroupUserAll() {
        List<Map<String, Object>> rows = usersMapper.queryGroupUserAll();

        // 将查询结果转换为 GroupUserDto
        List<GroupUserDto> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            GroupUserDto dto = new GroupUserDto();
            dto.setGroupName((String) row.get("groupName"));
            String userListStr = (String) row.get("userList");
            List<String> userList = Arrays.asList(userListStr.split(","));
            dto.setUserList(userList);
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<GroupUserDto> queryAccountListByReportGroup() {
        List<GroupUserDto> resList = new ArrayList<>();
        List<String> groupList = groupMapper.queryReportGroupsList();
        for (String groupName : groupList) {
            GroupUserDto dto = new GroupUserDto();
            dto.setGroupName(groupName);
            List<String> accountList = usersMapper.queryAccountByGroup(groupName);
            dto.setAccountList(accountList);
            resList.add(dto);
        }
        return resList;
    }

    @Override
    public UserByPageDto queryUserByPage(PageRequestQto qto) {
        UserByPageDto dto = new UserByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<UserExcelDto> list = usersMapper.queryUserByPage(qto);
        dto.setUserList(list);
        Integer dataCount = usersMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void download(HttpServletResponse response, PageRequestQto qto) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户统计报表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 获取数据
        List<UserExcelDto> list = usersMapper.queryUserByPage(qto);
        String template = "src/main/resources/templates/userTemplate.xlsx";

        // 使用 FastExcel 库生成 Excel 文件并写入到输出流
        try (OutputStream outputStream = response.getOutputStream()) {
            FastExcel.write(outputStream)  // 将输出流传入
                    .withTemplate(template)   // 使用模板
                    .sheet()                  // 默认一个 sheet
                    .doFill(list);            // 填充数据
        } catch (Exception e) {
            // 处理流操作异常
            log.info("downLoadByUser, 导出失败，流操作异常：", e);
            throw new BusinessException(500, "导出失败");
        }
    }

    @Override
    public void updateUser(UserDto qto) {
        Integer result = usersMapper.updateUser(qto);
        if (result != 1) {
            throw new BusinessException(500, "修改失败");
        }
    }

    @Override
    public void deleteUser(String account) throws Exception {
        Integer result = usersMapper.deleteUser(account);
        if (result != 1) {
            throw new BusinessException(500, "删除失败");
        }
    }
}
