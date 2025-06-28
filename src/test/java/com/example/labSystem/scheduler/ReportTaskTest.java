package com.example.labSystem.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportTaskTest {
    @Autowired
    ReportTask task;

    public void testReportTask() throws Exception {
        task.executeReportTask();
    }
}
