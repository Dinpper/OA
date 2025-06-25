package com.example.labSystem.service;

public interface SystemConfigService {
    String queryReportType();

    void updateReportType(String value);

    String queryIsSkipHolidays();

    void updateIsSkipHolidays(String value);

    boolean queryEnableNetWorkRestriction();

    void updateEnableNetWorkRestriction(Boolean value);
}
