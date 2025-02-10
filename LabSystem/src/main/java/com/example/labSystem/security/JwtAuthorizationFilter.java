//package com.example.labSystem.security;
//
//import com.example.labSystem.utils.JwtUtil;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import java.util.ArrayList;
//
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//
//
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
//
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//
//        String header = request.getHeader("Authorization");
//
//        if (header == null || !header.startsWith("Bearer ")) {
//
//            chain.doFilter(request, response);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//
//        String token = request.getHeader("Authorization");
//        if (token != null) {
//
//            Claims claims = JwtUtil.extractClaims(token.replace("Bearer ", ""));
//            if (claims != null) {
//
//                String username = claims.getSubject();
//
//                if (username != null) {
//
//                    return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//                }
//            }
//        }
//        return null;
//    }
//}
//
