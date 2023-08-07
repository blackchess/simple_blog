package com.liaoxin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.SignUpDTO;
import com.liaoxin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: liaoxin
 * @Date: 2022/7/14
 * @Description: 管理员用户管理控制器
 **/

@RestController
@RequestMapping("admin")
public class UmsAdminController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResultBean<UmsUser> list(@RequestBody PageDTO pageDTO){
        Page<UmsUser> page = new Page<>(pageDTO.getCurrent(), pageDTO.getSize());
        return ResultBean.success(userService.page(page));
    }

    @PutMapping("")
    public ResultBean update(@RequestBody SignUpDTO signUpDto){
        UmsUser umsUser = new UmsUser();
        BeanUtils.copyProperties(signUpDto, umsUser);
        if(userService.updateById(umsUser)){
            return ResultBean.success();
        }
        return ResultBean.failure();
    }

    @DeleteMapping("/{id}")
    public ResultBean delete(@PathVariable("id") Long userId){
        if(userService.removeById(userId)){
            return ResultBean.success();
        }
        return ResultBean.failure();
    }

}
