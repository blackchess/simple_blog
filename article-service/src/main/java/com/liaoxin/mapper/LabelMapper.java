package com.liaoxin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liaoxin.domain.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/21
 **/
@Mapper
public interface LabelMapper extends BaseMapper<Label> {

    List<Label> selectLabelWithArticle(@Param("id") Long id);

}
