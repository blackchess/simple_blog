package com.liaoxin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.*;


public interface UserService extends IService<UmsUser> {

    /**
     * 用户登录功能
     * @param signInDTO 登录参数类
     * @return  token字符串
     */
    String signInWithPassword(SignInDTO signInDTO);

    /**
     * 验证码登录
     * @param signInDTO
     * @return
     */
    String signInWithCode(SignInDTO signInDTO);

    /**
     * 手机注册功能
     */
    UmsUser signUpWithPhone(SignUpDTO signUpDto);

    /**
     * 邮件注册功能
     */
    UmsUser signUpWithMail(SignUpDTO signUpDTO);

    /**
     * 修改用户密码
     */
    int updatePassword(UpdatePasswordDTO dto);

    /**
     * 注销登录
     */
    Boolean logout(String token);

    /**
     * 带筛选条件的用户列表
     */
    Page<UmsUser> list(PageDTO pageDTO, UmsUser user);

}
