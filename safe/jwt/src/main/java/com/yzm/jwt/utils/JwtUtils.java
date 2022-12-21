package com.yzm.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtils implements Serializable {

    private static final long serialVersionUID = 8527289053988618229L;
    /**
     * token头
     * token前缀
     */
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Basic ";
    /**
     * 用户名称
     */
    public static final String USERNAME = Claims.SUBJECT;
    public static final String PASSWORD = "password";
    /**
     * 权限列表
     */
    public static final String AUTHORITIES = "authorities";
    /**
     * 密钥
     */
    private static final String SECRET = "abcdefg";
    private static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
    /**
     * 过期时间5分钟
     * 刷新时间2分钟
     */
    public static final long TOKEN_EXPIRED_TIME = 5 * 60 * 1000L;
    public static final long TOKEN_REFRESH_TIME = 2 * 60 * 1000L;

    public static String generateToken(Map<String, Object> claims) {
        return generateToken(claims, 0L);
    }

    /**
     * 生成令牌
     */
    public static String generateToken(Map<String, Object> claims, long expireTime) {
        if (expireTime <= 0L) expireTime = TOKEN_EXPIRED_TIME;

        // 标头默认配置，可以不写
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("typ", "JWT");
        headMap.put("alg", "HS256");

        return Jwts.builder()
                .setHeader(headMap)
                //JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击
                .setId(UUID.randomUUID().toString())
                //.setIssuer("该JWT的签发者，是否使用是可选的")
                //.setSubject("该JWT所面向的用户，是否使用是可选的")
                //.setAudience("接收该JWT的一方，是否使用是可选的")
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //签发时间(token生成时间)
                .setIssuedAt(new Date())
                //生效时间(在指定时间之前令牌是无效的)
                .setNotBefore(new Date())
                //过期时间(在指定时间之后令牌是无效的)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                //设置签名使用的签名算法和签名使用的秘钥
//                .signWith(SignatureAlgorithm.HS256, SECRET)
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .compact();
    }

    /**
     * 验证令牌
     */
    public static Claims verifyToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    //签名秘钥
//                    .setSigningKey(SECRET)
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // token过期是直接抛出异常的，但仍然可以获取到claims对象
            claims = e.getClaims();
        }

        //和当前时间进行对比来判断是否过期
        if (new Date().before(claims.getExpiration()))
            return claims;
        return null;
    }

    /**
     * 通过Base64编码
     * 由密钥生成加密key
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(SECRET);
//        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return new SecretKeySpec(encodedKey, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 从令牌中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = verifyToken(token);
        return claims == null ? null : claims.getSubject();
    }

    /**
     * 获取请求token
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token == null) token = request.getHeader("token");
        if (token == null || "".equals(token)) return null;

        if (token.startsWith(TOKEN_PREFIX)) {
            token = token.substring(TOKEN_PREFIX.length());
        }
        return token;
    }

    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, "admin");
        claims.put(PASSWORD, 123456);

        //生成token
        String token = generateToken(claims);
        System.out.println(token);

        //解析
        Claims verify = verifyToken(token);
        System.out.println(verify.get(USERNAME, String.class));
        System.out.println(verify.get(PASSWORD, Integer.class));
        System.out.println(verify.getSubject());
        System.out.println(verify.getIssuedAt());
        System.out.println(verify.getExpiration());
    }

}
