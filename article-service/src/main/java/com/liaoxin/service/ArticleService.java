package com.liaoxin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.vo.ArticleVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/22
 **/
public interface ArticleService extends IService<Article> {
    /**
     * 分页查询文章信息
     * @param pageDTO   分页参数
     * @param keyword   关键字
     * @param userId    用户主键
     */
    Page<ArticleVo> selectArticleList(PageDTO pageDTO, String keyword, Long userId);

    /**
     *
     * @param pageDTO
     * @param order 排序（1：升序 0：降序）
     * @return
     */
    Page<Article> selectHotArticle(PageDTO pageDTO, String order);

    /**
     * 通过文章ID查询文章
     * @param id 文章ID
     */
    ArticleVo selectArticle(Long id);

    /**
     * 通过标签ID查询文章
     * @param id 标签ID
     */
    List<ArticleVo> selectArticleWithLabel(Long id);

    /**
     * 按（年、月、日）统计文章数量
     * @param time
     * @return
     */
    //List articleCountWith(String time);

}
