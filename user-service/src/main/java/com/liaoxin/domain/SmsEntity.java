package com.liaoxin.domain;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsEntity implements Serializable {

    /**
     * 手机号码
     */
    private String mobileNum;
    /**
     * 发送信息
     */
    private String message;

}
