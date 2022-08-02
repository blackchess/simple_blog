package com.liaoxin.common.utils;

import java.util.Random;

public class CodeUtils {
    //生成n位验证码
    public static String createCode(int n){
        Random random = new Random();
        StringBuffer codeString =new StringBuffer();
        for(int i = 0;i < n;i ++){
            int num = random.nextInt(10);
            codeString.append(num);
        }
        return codeString.toString();
    }
}
