package com.liaoxin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liaoxin.domain.CommentDomain;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/13
 **/
@Mapper
public interface CommentMapper extends BaseMapper<CommentDomain> {
}
