package com.liaoxin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleVo> selectArticleWithLabel(@Param("id") Long id);

}
