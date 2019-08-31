package com.wxy.util;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class TokenHelper {

    public static ThreadLocal<String> tokenLocal = new ThreadLocal<>();

    /**
     * 生成token
     *
     * @param username
     * @param userId
     * @return
     */
    public static String createToken(String username, Long userId) {
        Assert.hasText(username, "The parameter username is required");
        Assert.notNull(userId, "The parameter userId is required");
        String sign = JwtUtils.sign(username, String.valueOf(userId));
        tokenLocal.set(sign);
        return sign;
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static boolean checkToken(String token) {
        Assert.hasText(token, "The parameter token is required");
        boolean verity = JwtUtils.verity(token);
        if (verity) {
            tokenLocal.set(token);
        }
        return verity;
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public static Long getUserId() {
        String userId = JwtUtils.getUserId(tokenLocal.get());
        return StringUtils.isEmpty(userId) ? null : Long.valueOf(userId);
    }
}
