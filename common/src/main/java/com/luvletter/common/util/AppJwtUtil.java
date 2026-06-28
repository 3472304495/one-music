package com.luvletter.common.util;

import io.jsonwebtoken.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AppJwtUtil {

    // TOKEN的有效期一天（S）
    private static final int TOKEN_TIME_OUT = 3_600;
    // 加密KEY - 使用至少64字节的字符串确保HS512安全性
    private static final String TOKEN_ENCRY_KEY = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjYxMDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjYxMDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
    // 最小刷新间隔(S)
    private static final int REFRESH_TIME = 300;

    /**
     * 生成JWT Token
     * @param id 用户ID
     * @return JWT Token字符串
     */
    public static String getToken(Long id) {
        Map<String, Object> claimMaps = new HashMap<>();
        claimMaps.put("id", id);
        long currentTime = System.currentTimeMillis();

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTime))
                .setSubject("system")
                .setIssuer("xiaohuai")
                .setAudience("system")
                .compressWith(CompressionCodecs.GZIP)
                .signWith(SignatureAlgorithm.HS512, generalKey())
                .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 1000))
                .addClaims(claimMaps)
                .compact();
    }

    /**
     * 获取token中的claims信息
     * @param token JWT Token
     * @return Jws<Claims> 对象
     */
    private static Jws<Claims> getJws(String token) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token);
    }

    /**
     * 获取payload body信息
     * @param token JWT Token
     * @return Claims 对象
     */
    public static Claims getClaimsBody(String token) {
        try {
            return getJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    /**
     * 验证token是否过期
     * @param claims Claims对象
     * @return -1：有效且无需刷新，0：有效但需要刷新，1：过期，2：其他异常
     */
    public static int verifyToken(Claims claims) {
        if (claims == null) {
            return 1;
        }
        try {
            if (claims.getExpiration().before(new Date())) {
                return 1; // 已过期
            }
            // 需要自动刷新TOKEN
            if ((claims.getExpiration().getTime() - System.currentTimeMillis()) > REFRESH_TIME * 1000) {
                return -1; // 有效且无需刷新
            } else {
                return 0; // 有效但需要刷新
            }
        } catch (ExpiredJwtException ex) {
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    /**
     * 由固定字符串生成符合HS512要求的加密key
     * @return SecretKey 对象
     */
    public static SecretKey generalKey() {
        byte[] keyBytes = TOKEN_ENCRY_KEY.getBytes(StandardCharsets.UTF_8);
        // 确保密钥长度满足HS512要求(至少512位/64字节)
        if (keyBytes.length < 64) {
            byte[] extendedKey = new byte[64];
            int offset = 0;
            while (offset < 64) {
                System.arraycopy(keyBytes, 0, extendedKey, offset,
                        Math.min(keyBytes.length, 64 - offset));
                offset += keyBytes.length;
            }
            keyBytes = extendedKey;
        }
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }
}
