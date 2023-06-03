package com.liaoxin.client;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.UmsUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: liaoxin
 * @Date: 2022/11/15
 * @Description: 用户服务调用
 **/
@Component
@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/user/{id}")
    ResultBean<UmsUser> findUserById(@PathVariable("id") Long uid);
}
