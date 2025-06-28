package com.example.labSystem.mapper;

import com.example.labSystem.mappers.RecordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordMapperTest {
    @Autowired
    RecordMapper mapper;
    @Test
    public void testRecord(){
        mapper.automaticSignOut(50384);
        mapper.automaticSignOut(50402);
        mapper.automaticSignOut(50411);
    }
}
