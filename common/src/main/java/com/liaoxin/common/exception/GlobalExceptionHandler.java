package com.liaoxin.common.exception;

import com.liaoxin.common.common.ResultBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/8
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResultBean handler(AppException e){
        e.printStackTrace();
       return ResultBean.failure(e.getCode(), e.getMessage());
    }

}
