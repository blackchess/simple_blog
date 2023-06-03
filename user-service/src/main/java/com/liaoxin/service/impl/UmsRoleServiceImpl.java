package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.domain.UmsRole;
import com.liaoxin.mapper.UmsRoleMapper;
import com.liaoxin.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/10/8
 **/
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    UmsRoleMapper roleMapper;

    @Override
    public List<UmsRole> findRoleListWithUser(Long id) {
        return roleMapper.selectRoleListWithUser(id);
    }
}
