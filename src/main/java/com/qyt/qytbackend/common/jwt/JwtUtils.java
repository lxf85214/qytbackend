package com.qyt.qytbackend.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
@Component
public class JwtUtils {

    // 密钥，实际项目中应该从配置文件中读取
    private static final String SECRET_KEY = "qytbackendsecretkeyqytbackendsecretkeyqytbackendsecretkey";

    // 过期时间，7天
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    /**
     * 生成token
     * @param userId 用户ID
     * @return token
     */
    public String generateToken(Long userId) {
        // 设置claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        // 生成token
        return Jwts.builder()
                .claims(claims)
                .subject(userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 验证Token
     * @param token token字符串
     * @return Claims对象
     */
    public Claims verifyToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 解析Token
     * @param token token字符串
     * @return Claims对象
     */
    public Claims parseToken(String token) {
        return verifyToken(token);
    }

    /**
     * 从Token中获取用户ID
     * @param token token字符串
     * @return 用户ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = verifyToken(token);
        return Long.parseLong(claims.getSubject());
    }

    /**
     * 检查Token是否过期
     * @param token token字符串
     * @return 是否过期
     */
    public boolean isTokenExpired(String token) {
        Claims claims = verifyToken(token);
        return claims.getExpiration().before(new Date());
    }

    /**
     * 获取签名密钥
     * @return 密钥
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}