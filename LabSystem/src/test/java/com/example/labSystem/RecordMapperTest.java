package com.example.labSystem;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.mappers.RecordMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RecordMapperTest {

    @Autowired
    private RecordMapper recordMapper;

    @Test
    public void testQueryStatusType() {
        CommonRequestQto qto = new CommonRequestQto();
        qto.setUserCode("testUser");
        Integer statusType = recordMapper.queryStatusType(qto);
        System.out.println("Status Type: " + statusType);
    }



    @Test
    void testRecordMapper() {
        assertNotNull(recordMapper, "RecordMapper should not be null");
    }


}
