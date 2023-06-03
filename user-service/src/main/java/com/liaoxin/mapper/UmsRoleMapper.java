package com.liaoxin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liaoxin.domain.UmsRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/10/8
 **/
@Mapper
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 查询用户角色列表
     * @param id 用户ID
     * @return
     */
    List<UmsRole> selectRoleListWithUser(Long id);

}
