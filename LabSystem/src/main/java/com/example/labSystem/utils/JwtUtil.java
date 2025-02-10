//package com.example.labSystem.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//
//public class JwtUtil {
//
//    private static final String SECRET = "your_secret_key"; // 应该存储在安全的地方
//
//    public static String generateToken(String username, long expirationTime) {
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .compact();
//    }
//
//    public static Claims extractClaims(String token) {
//
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
//
