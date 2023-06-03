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
    Boolean updateArticleBPO(ArticleVo articleVo);

    /**
     * 新增文章
     * @param articleVo
     * @return
     */
    Boolean insertArticleBPO(ArticleVo articleVo);

    /**
     * 包含标签、图片的文章列表
     * @param pageDTO
     * @return
     */
    Page<ArticleVo> articleVoList(PageDTO pageDTO);

}
