package com.liaoxin.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @Auther: liaoxin
 * @Date: 2023/5/18
 * @Description: 统一网关异常处理
 **/

@Component
public class GlobalApiExceptionHandler implements ErrorWebExceptionHandler {

    @Resource
    private ObjectMapper objectMapper;

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        //响应提交处理
        if(response.isCommitted()){
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                //返回响应结果
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(resultBean(ex)));
            }
            catch (JsonProcessingException e) {
                LOGGER.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

    /**
     * 封装结果类
     * @param ex    异常信息
     */
    private ResultBean resultBean(Throwable ex){
        //业务异常处理
        if(ex instanceof AppException){
            return ResultBean.failure(((AppException)ex).getCode(), ex.getMessage());
        }
        return ResultBean.failure(500, ex.getMessage());

    }
}
