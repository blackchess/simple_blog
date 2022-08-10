package com.liaoxin.config;

import com.liaoxin.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/22
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/article/**")
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/signUp")
                .excludePathPatterns("/user/signIn");
    }
}
