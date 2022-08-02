package com.liaoxin.common.common;



import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {


    private int code;

    private String message;

    private T data;

    private ResultBean(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> ResultBean success(){
        return new ResultBean(200,"success",null);
    }

    public static<T> ResultBean success(T data){
        return new ResultBean(200,"success",data);
    }

    public static<T> ResultBean success(String message){
        return new ResultBean(200,message,null);
    }

    public static<T> ResultBean success(String message, T data){
        return new ResultBean(200,message,data);
    }

    public static<T> ResultBean failure(){
        return new ResultBean(500, "服务器错误", null);
    }

    public static<T> ResultBean failure(int code, String message){
        return new ResultBean(code,message,null);
    }


}
