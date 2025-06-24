package com.example.labSystem;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@ComponentScan("com.example.labSystem.mapper")
//@MapperScan("com.example.labSystem.mappers")
@EnableTransactionManagement
public class LabSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabSystemApplication.class, args);
	}
}
