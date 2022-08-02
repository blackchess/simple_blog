package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.domain.Label;
import com.liaoxin.mapper.LabelMapper;
import com.liaoxin.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/21
 **/
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Autowired
    LabelMapper labelMapper;

    @Override
    public List<Label> selectLabelWithArticle(Long id) {
        return labelMapper.selectLabelWithArticle(id);
    }
}
