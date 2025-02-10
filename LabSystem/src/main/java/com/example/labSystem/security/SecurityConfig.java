//package com.example.labSystem.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityContext() // 配置安全上下文
//                .requireExplicitSave(true) // 强制保存安全上下文
//                .and()
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll() // 允许所有用户访问 /auth/** 路径
//                .anyRequest().authenticated() // 其他请求需要认证
//                .and()
//                .csrf().disable(); // 禁用 CSRF 保护
//
//        return http.build(); // 返回配置的 SecurityFilterChain 对象
//    }
//
//    // 可以根据需要自定义一个 UserDetailsService，用于用户的认证和授权
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user")
//                        .password(passwordEncoder().encode("password")) // 加密密码
//                        .roles("USER")
//                        .build()
//        );
//    }
//}
