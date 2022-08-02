package com.liaoxin.common.api;

import java.io.Serializable;

public enum ResultCode implements Serializable {

    FAILED(500, "操作失败"),
    SUCCESS(200, "操作成功"),
    FORBIDDEN(403, "没有相关权限"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    VALIDATE_FAILED(404, "参数检验失败");

    private int code;

    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
