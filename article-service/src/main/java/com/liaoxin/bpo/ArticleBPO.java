package com.liaoxin.bpo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liaoxin.domain.vo.ArticleVo;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/2
 **/

public interface ArticleBPO {

    /**
     * 更新文章
     * @param articleVo
     * @return
     */
    void updateArticleBPO(ArticleVo articleVo);

    /**
     * 新增文章
     * @param articleVo
     * @return
     */
    void insertArticleBPO(ArticleVo articleVo);

    /**
     * 包含标签、图片的文章列表
     * @param pageDTO
     * @return
     */
    Page<ArticleVo> articleVoList(PageDTO pageDTO);

    /**
     * 批量删除文章
     * @param userId        用户主键
     * @param articleIds    文章主键列
     */
    void deleteArticle(Long userId, List<String> articleIds);

}
