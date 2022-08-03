package com.liaoxin.bpo;

import com.liaoxin.domain.vo.ArticleVo;

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

}
