//package com.example.labSystem.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 全局跨域配置
// */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 覆盖所有请求
//        registry.addMapping("/**")
//                // 放行哪些域名（必须用 patterns，否则 * 会和 allowCredentials 冲突）
//                .allowedOrigins("http://127.0.0.1:5173","http://localhost:5173",
//                        "http://172.22.236.90","http://172.22.236.90:80")
//                // 允许发送 Cookie
//                .allowCredentials(true)
//                .allowedMethods("*")
//                //跨域允许时间
//                .maxAge(3600);
//    }
//}
