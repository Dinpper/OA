package com.example.labSystem.service.impl;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupByPageDto;
import com.example.labSystem.dto.GroupDto;
import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.mappers.GroupMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.GroupService;
import com.example.labSystem.utils.DownloadUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<String> queryGroupsList() {
        List<String> list = groupMapper.queryGroupsList();
        return list;
    }

    @Override
    public GroupByPageDto queryGroupsByPage(PageRequestQto qto) {
        GroupByPageDto dto = new GroupByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<GroupDto> list = groupMapper.queryGroupsByPage(qto);
        dto.setGrouplist(list);
        Integer dataCount = groupMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void download(HttpServletResponse response, PageRequestQto qto) throws Exception {
        String fileName = "小组统计报表";
        String template = Constants.TEMPLATE_PATH + Constants.GROUP_TEMPLATE_EXCEL_XLSX;
        List<GroupDto> list = groupMapper.queryGroupsByPage(qto);
        DownloadUtil.downloadXlsx(response, fileName, template, list);
    }

    @Override
    public void updateGroup(GroupDto qto) {
        Integer result = groupMapper.updateGroup(qto);
        if (result != 1 ) {
            throw new BusinessException(500, "修改失败");
        }
    }

    @Override
    public void deleteGroup(String groupName) throws Exception{
        Integer result = groupMapper.deleteGroup(groupName);
        if (result != 1 ) {
            throw new BusinessException(500, "删除失败");
        }
    }

    @Override
    public void addGroup(CommonRequestQto qto) {
        GroupDto dto = new GroupDto();
        dto.setGroupName(qto.getGroupName());
        if (StringUtils.isEmpty(qto.getLeaderName())) {
            throw new BusinessException(399, "参数错误");
        }
        String leaderAccount = usersMapper.queryAccountByUserName(qto.getLeaderName());

        //TODO 两个人名字一样未处理

        dto.setLeaderAccount(leaderAccount);
        dto.setDeleteFlag(0);
        if (qto.getReportFlag() == null) {
            throw new BusinessException(399, "参数错误");
        }
        dto.setReportFlag(qto.getReportFlag());

        Integer result = groupMapper.insert(dto);
        if (result != 1) {
            throw new BusinessException(500, "添加失败");
        }

    }
}
