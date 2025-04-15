package com.example.labSystem.mappers;


import com.example.labSystem.domain.Record;
import com.example.labSystem.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RecordMapper {

    /**
     * 查询用户今年一年的签到时间
     * @param account 用户账号
     * @return
     */
    Double querySignDurationYearTotal(@Param("account") String account);
    Integer queryStatusType(@Param("account") String account);

    int insert(Record record);

    Integer attendanceCheckIn(@Param("account") String account);

    Integer attendanceCheckOut(@Param("account") String account);

    Double querySignDurationToDayAll(@Param("account") String account);
    Double querySignDurationWeekAll(@Param("account") String account);
    Double querySignDurationMonthAll(@Param("account") String account);

    List<RecordSonDto> querySignDurationWeek(@Param("account") String account);

    Integer querySignDurationWeekByUser(CommonRequestQto qto);

    List<RecordSonDto> queryGroupSignDuration();

    RecordDto queryOverdueSignInRecords();

    Integer updateIsReminded(@Param("recordId") Integer recordId);

    Integer querySignDurationCountByPage(PageRequestQto qto);

    List<RecordExcelDto> querySignDurationByPage(PageRequestQto qto);

    Integer queryRecordCountByPage(PageRequestQto qto);

    List<RecordExcelDto> queryRecordByPage(PageRequestQto qto);

    List<Record> queryNotSignOutToday();

    Integer automaticSignOut(@Param("recordId") Integer recordId);

    List<RecordDto> SignOutReminderUserList();
}
