package com.yuan.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author yuan
 * @date 2019/10/19 6:15 下午
 * 加密工具类
 */
public class EncryptUtil {

    /**
     * md5加密
     *
     * @param source         加密数据
     * @param salt           盐
     * @param hashIterations 散列次数
     * @return 加密字符串
     */
    public static String md5(Object source, Object salt, int hashIterations) {
        return new Md5Hash(source, salt, hashIterations).toString();
    }

    /**
     * 生成随机salt
     *
     * @return
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void main(String[] args) {
        String[] salts = {
                "AL0SRsKlstGZuA/W0dTb3A==",
                "IHVYPYIy0ro0o15+Lcck/Q==",
                "wEVye1xpPMDdO1z0m1uYRQ=="
        };
        for (String salt : salts) {
            System.out.println(md5("123456", salt, 2));
        }
    }
}
