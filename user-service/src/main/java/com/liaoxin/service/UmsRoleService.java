package com.liaoxin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.UmsRole;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/10/8
 **/
public interface UmsRoleService extends IService<UmsRole> {

    /**
     * 查询用户角色
     * @param id 用户ID
     * @return
     */
    List<UmsRole> findRoleListWithUser(Long id);

}
