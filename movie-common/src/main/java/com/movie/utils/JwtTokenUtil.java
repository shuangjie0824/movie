package com.movie.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class JwtTokenUtil {

    //在http header中的名字
    public final static String TOKEN_HEADER = "Authorization";

    //一个星期过期
    public final static long REMEMBER_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    //一天过期
    public final static long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 应用密钥
    private static final String APP_SECRET = "acfd465595224d4db1b23577c3378164";

    // 角色权限声明
    private static final String ROLE_CLAIMS = "roles";

    /**
     * 生成Token
     */
    public static String createToken(String username, List<String> roles, long expiration) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, roles);

        SecretKey secretKey = Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setSubject(username)
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    /**
     * 获取token body
     */
    private static Claims getTokenClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(APP_SECRET).build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch ( ExpiredJwtException e ) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token) {
        return getTokenClaims(token).getSubject();
    }

    /**
     * 从Token中获取用户角色
     */
    public static List<String> getTokenRoles(String token) {
        List<String> roles = new ArrayList<>();
        Object object = getTokenClaims(token).get(ROLE_CLAIMS);
        if (object instanceof ArrayList<?>) {
            for (Object o : (List<?>) object) {
                roles.add((String) o);
            }
        }
        return roles;
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) {
        return getTokenClaims(token).getExpiration().before(new Date());
    }

}
