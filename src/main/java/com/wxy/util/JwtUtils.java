package com.wxy.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author wxy
 * @Date 19-7-9 下午2:44
 * @Description token认证工具
 **/
public class JwtUtils {

    /**
     * 过期时间15分钟
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "d195265928a14f9a89dc50c71ce705f0";


    /**
     * 生成签名 15分钟后过期
     *
     * @param username 用户名
     * @param userId   用户ID
     * @return
     */
    public static String sign(String username, String userId) {
        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            HashMap<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 附带username,userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验token是否正确
     *
     * @param token 校验的token
     * @return
     */
    public static boolean verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取用户登录账号
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户登录ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }
}

