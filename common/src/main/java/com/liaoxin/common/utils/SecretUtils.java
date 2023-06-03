package com.liaoxin.common.utils;

import com.liaoxin.common.common.WebConst;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.nio.charset.StandardCharsets;

public class SecretUtils {

    public static String MD5Encoding(String str, String MD5Secret){
        String secretPassword = str + MD5Secret;
        return DigestUtils.md5DigestAsHex(secretPassword.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        System.out.println(SecretUtils.MD5Encoding("123456", WebConst.SECURT));
    }

}
