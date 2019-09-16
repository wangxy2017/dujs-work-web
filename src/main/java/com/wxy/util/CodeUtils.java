package com.wxy.util;

import java.util.Random;

/**
 * @Author wangxy
 * @Date 2019/6/17 11:55
 * @Description 验证码工具类
 **/
public class CodeUtils {
    /**
     * 生成指定长度随机字符串
     *
     * @param length
     * @return
     */
    public static String randomCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度字符串
     *
     * @param length
     * @return
     */
    public static String randomStr(int length) {
        char[] code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(code[new Random().nextInt(code.length)]);
        }
        return sb.toString();
    }
}
