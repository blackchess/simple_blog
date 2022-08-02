package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.mapper.ArticleImageMapper;
import com.liaoxin.service.ArticleImageService;
import org.springframework.stereotype.Service;

@Service
public class ArticleImageServiceImpl extends ServiceImpl<ArticleImageMapper, ArticleImage> implements ArticleImageService {
}
