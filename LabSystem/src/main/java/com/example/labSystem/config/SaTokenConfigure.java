//package com.example.labSystem.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import cn.dev33.satoken.interceptor.SaInterceptor;
//import cn.dev33.satoken.router.SaRouter;
//import cn.dev33.satoken.stp.StpUtil;
//
//@Configuration
//public class SaTokenConfigure implements WebMvcConfigurer {
//
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        // 注册 Sa-Token 拦截器，定义详细认证规则
////        registry.addInterceptor(new SaInterceptor(handle -> {
////            // 登录接口不需要认证
////            SaRouter.match("/login/**", r -> r.stop());
////            // 其他所有接口都需要认证
////            SaRouter.match("/**", r -> StpUtil.checkLogin());
////        })).addPathPatterns("/**");
////    }
//}