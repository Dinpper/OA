package com.example.labSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("com.example.labSystem.mapper")
//@MapperScan("com.example.labSystem.mappers")

public class LabSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabSystemApplication.class, args);
	}
}
