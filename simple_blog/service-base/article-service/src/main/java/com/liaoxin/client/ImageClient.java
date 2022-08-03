package com.liaoxin.client;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.ArticleImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "image-service")
public interface ImageClient {

    @GetMapping(value = "/articleImage/article/{id}")
    ResultBean<List<ArticleImage>> findImageByArticle(@PathVariable("id")Long id);

}
