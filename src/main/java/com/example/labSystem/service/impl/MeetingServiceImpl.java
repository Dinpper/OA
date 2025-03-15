package com.example.labSystem.service.impl;

import com.example.labSystem.Enum.FileTypeEnum;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.Constants;
import com.example.labSystem.domain.FileRecord;
import com.example.labSystem.domain.UserMeeting;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.FileRecordMapper;
import com.example.labSystem.mappers.MeetingsMapper;
import com.example.labSystem.mappers.UserMeetingsMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.MeetingService;
import com.example.labSystem.utils.DownloadUtil;
import com.example.labSystem.utils.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingsMapper meetingsMapper;

    @Autowired
    private UserMeetingsMapper userMeetingsMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private FileRecordMapper fileRecordMapper;

    @Value("${fileStorage.rootDir}")
    private String uploadDir;

    @Override
    public void addMeeting(MeetingsDto qto) {
        if (qto.getMemberList() == null) {
            throw new BusinessException(399, "未选择参会人员");
        }
        StringBuilder memberName = new StringBuilder();
        String concatenatedString = String.join(",", qto.getMemberList());
        String date = qto.getStartTime().substring(0, 10); // 取前10个字符
        qto.setReportDate(date);
        Integer result = meetingsMapper.insert(qto);
        if (result != 1) {
            throw new BusinessException(500, "添加失败");
        }
        Integer meetingId = qto.getMeetingId();
        UserMeeting userMeetings = new UserMeeting();
        userMeetings.setMeetingId(meetingId);
        qto.getMemberList().forEach(l -> {
            String account = usersMapper.queryAccountByUserName(l);
            userMeetings.setAccount(account);
            userMeetingsMapper.insert(userMeetings);
        });
    }

    @Override
    public MeetingsDto queryMeetingNew(CommonRequestQto qto) throws Exception {
        MeetingsDto dto = meetingsMapper.queryMeetingNew(qto.getAccount());
        if (dto == null) {
            return null;
        }
        dto.setOrganizerName(usersMapper.queryUserNameByAccount(dto.getOrganizerAccount()));
        List<String> memberList = userMeetingsMapper.queryMembersName(dto.getMeetingId());
        String membersName = String.join(",", memberList);
        dto.setMembersName(membersName);
        return dto;
    }

    @Override
    public List<MeetingsDto> queryMeetingByDate(CommonRequestQto qto) throws Exception {
        List<MeetingsDto> resList = meetingsMapper.queryMeetingByDate(qto.getAccount(), qto.getQueryDate());
        resList.forEach(l -> {
            l.setOrganizerName(usersMapper.queryUserNameByAccount(l.getOrganizerAccount()));
            List<String> memberList = userMeetingsMapper.queryMembersName(l.getMeetingId());
            String membersName = String.join(",", memberList);
            l.setMembersName(membersName);
        });
        return resList;
    }


    @Override
    public MeetingsByPageDto queryMeetingByPage(PageRequestQto qto) throws Exception {
        MeetingsByPageDto dto = new MeetingsByPageDto();
        if (qto.getSize() == null) {
            qto.setSize(10);
        }
        if (qto.getPage() == null) {
            qto.setPage(1);
        }
        qto.setOffset(qto.getSize() * (qto.getPage() - 1));
        dto.setSize(qto.getSize());
        dto.setPage(qto.getPage());
        List<MeetingsDto> list = meetingsMapper.queryMeetingByPage(qto);
        dto.setMeetingsList(list);
        Integer dataCount = meetingsMapper.queryCountByPage(qto);
        dto.setDataCount(dataCount);
        Integer pageCount = (dataCount + qto.getSize() - 1) / qto.getSize();
        dto.setPageCount(pageCount);
        return dto;
    }

    @Override
    public void download(HttpServletResponse response, PageRequestQto qto) throws Exception {
        String fileName = "小组统计报表";
        String template = Constants.TEMPLATE_PATH + Constants.GROUP_TEMPLATE_EXCEL_XLSX;
        List<MeetingsDto> list = meetingsMapper.queryMeetingByPage(qto);
        DownloadUtil.downloadXlsx(response, fileName, template, list);
    }

    @Override
    public void uploadMultiple(FileRecord fileRecord, List<MultipartFile> files) throws Exception {
        if (files == null) {
            return;
        }
        String filePath = uploadDir + FileUtil.generateFilePath(FileTypeEnum.getDesc(2), "");
        FileUtil.uploadBatch(files, filePath);

        fileRecord.setFilePath(filePath);
        fileRecord.setVisibility(1);
        fileRecord.setSourceType(2);
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            fileRecord.setFileName(fileName);
            fileRecord.setFileType(FileUtil.getFileType(fileName));
            fileRecordMapper.fileSubmit(fileRecord);
//                FileUtil.getFormattedFileSize();

        }
    }

    @Override
    public void updateSummary(MeetingsDto qto) throws Exception {
        Integer result = meetingsMapper.updateSummary(qto.getMeetingId(), qto.getSummary());
        if (result != 1) {
            throw new BusinessException(500, "添加失败");
        }
    }
}
