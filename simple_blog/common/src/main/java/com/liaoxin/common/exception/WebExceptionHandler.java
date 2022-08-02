package com.liaoxin.common.exception;

import com.liaoxin.common.common.ResultBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/22
 **/
@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResultBean AppExceptionHandler(AppException appException){
        return ResultBean.failure();
    }

}
