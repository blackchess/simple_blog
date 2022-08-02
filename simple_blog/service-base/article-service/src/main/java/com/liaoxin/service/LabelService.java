package com.liaoxin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/21
 **/
public interface LabelService extends IService<Label> {

    /**
     * 查询文章对应的标签
     * @param id 文章ID
     */
    List<Label> selectLabelWithArticle(@Param("id") Long id);

}
