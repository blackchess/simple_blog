package com.liaoxin.service.bpo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.model.dto.SignInDTO;
import com.liaoxin.model.dto.SignUpDTO;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/8
 **/
public interface UserBPO extends IService<UmsUser> {

    /**
     * 注册功能
     * @param signUpDTO
     * @return
     */
    UmsUser signUp(SignUpDTO signUpDTO);

    /**
     * 登录功能
     * @param signInDTO
     * @return
     */
    String signIn(SignInDTO signInDTO);

}
