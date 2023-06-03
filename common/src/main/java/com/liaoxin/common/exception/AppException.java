package com.liaoxin.common.exception;

import lombok.Data;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/22
 **/
@Data
public class AppException extends RuntimeException{

    private int code;

    private String message;

    public AppException(String message) {
        super(message);
        this.message = message;
    }

    public AppException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
