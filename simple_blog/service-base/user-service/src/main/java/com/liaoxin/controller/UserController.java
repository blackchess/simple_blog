package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.*;
import com.liaoxin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/signUp")
    public ResultBean<UmsUser> signUp(@RequestBody UmsUserDto umsUserDto){
        UmsUser result = userService.signUp(umsUserDto);
        if(result == null){
            return ResultBean.failure();
        }else{
            return ResultBean.success(result);
        }
    }

    @PostMapping("/mailSignUp")
    public ResultBean<UmsUser> MailsignUp(@RequestBody MailSignUpDTO mailSignUpDTO){
        UmsUser result = userService.signUpFromMail(mailSignUpDTO);
        if(result != null){
            return ResultBean.success(result);
        }else{
            return ResultBean.failure();
        }
    }

    @PostMapping("/signIn")
    public ResultBean signIn(@RequestBody UmsSignInDto signInDto, HttpServletRequest request){
        UmsUser r1 = (UmsUser) request.getAttribute("userDTO");
        String token = userService.signIn(signInDto);
        if(token == null){
            return ResultBean.failure(403,"用户名或密码错误");
        }
        Map<String, String> result = new HashMap();
        result.put("tokenHeader","Bearer");
        result.put("token",token);
        return ResultBean.success(result);
    }

    @GetMapping("/list")
    public ResultBean<Page<UmsUser>> list(@RequestBody PageDTO pageDTO){
        QueryWrapper<UmsUser> wrapper = new QueryWrapper();
        wrapper.select(UmsUser.class, info -> !info.getColumn().equals("password"));
        if(pageDTO.getSearchWord() != null){
            wrapper.like("nick_name",pageDTO.getSearchWord());
        }
        return ResultBean.success(userService.page(new Page(pageDTO.getPageNum(), pageDTO.getPageSize()),wrapper));
    }

    @GetMapping("/{id}")
    public ResultBean<UmsUser> getUser(@PathVariable("id") Long id){
        return ResultBean.success(userService.getById(id));
    }

    @PutMapping("")
    public ResultBean update(@RequestBody UmsUpdateDTO updateDTO){
        UmsUser umsUser = new UmsUser();

        umsUser.setLastTime(new Date());
        BeanUtils.copyProperties(updateDTO, umsUser);
        if(userService.updateById(umsUser)){
            return ResultBean.success();
        }else{
            return ResultBean.failure();
        }
    }

    @GetMapping("/logout")
    public ResultBean logout(HttpServletRequest request){
        String token = request.getHeader("Bearer");
        if(!userService.logout(token)){
            return ResultBean.failure(403,"用户已经注销");
        }
        return ResultBean.success(true);
    }

    @PutMapping("/password/{id}")
    public ResultBean updatePassword(@RequestBody UpdatePasswordDTO dto){
        int result = userService.updatePassword(dto);
        if(result > 0){
            return ResultBean.success();
        }
        return ResultBean.failure();
    }
}
