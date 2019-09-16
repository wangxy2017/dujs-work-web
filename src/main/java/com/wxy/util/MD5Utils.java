package com.wxy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author wangxy
 * @Date 2019/6/17 11:05
 * @Description MD5工具类
 **/
public class MD5Utils {
    /**
     * 加密
     *
     * @param password 密码
     * @return
     */
    public static String encrypt(String password) {
        StringBuilder sb = null;
        try {
            //创建加密对象
            //参数1: 算法名字
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //进行加密  返回加密之后结果也是字节
            byte[] digest = messageDigest.digest(password.getBytes());
            sb = new StringBuilder();
            for (byte b : digest) {
                //位运算
                int len = b & 0xff;    //0  0x0 0x1 0x2 0x3 0x4 0x9  10  0xa   15  0xf  16 0x10 170x11
                if (len <= 15) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(len));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 加盐加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encrypt(String password, String salt) {
        return encrypt(password + salt);
    }
}
