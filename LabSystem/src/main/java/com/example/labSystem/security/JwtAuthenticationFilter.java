//package com.example.labSystem.security;
//
//import com.example.labSystem.utils.JwtUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        // 从请求中获取用户名和密码
//        try {
//
//            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
//
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//            );
//        } catch (IOException e) {
//
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//
//        String token = JwtUtil.generateToken(((UserDetails) authResult.getPrincipal()).getUsername(), 3600000); // 有效期为1小时
//        response.addHeader("Authorization", "Bearer " + token);
//    }
//}
//
