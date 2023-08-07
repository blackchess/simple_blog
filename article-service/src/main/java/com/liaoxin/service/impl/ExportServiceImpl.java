package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.Export;
import com.liaoxin.service.ArticleService;
import com.liaoxin.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liaoxin
 * @Date: 2022/9/30
 **/
@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    ArticleService articleService;

    @Override
    public void exportExcel(Map paramMap, HttpServletResponse response) {

        Export export = new Export();
        List<Article> articleList = articleService.list();
        export.setDataList(articleList);
        try {
            if(StringUtils.isBlank((String) paramMap.get("filename"))){
                export.setFilename(URLEncoder.encode((String) paramMap.get("导出文件"), "UTF-8"));
            }
            export.setFilename(URLEncoder.encode((String) paramMap.get("filename"), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.setContentType("application/octet-stream");
//        response.setHeader("Content-disposition", "attachment;filename=" + export.getFilename());
//        try {
//            EasyExcel.write(response.getOutputStream(),Article.class).head(export.getHeadList())
//            .autoCloseStream(Boolean.FALSE)
//            .sheet("sheet1")
//            .doWrite(export.getDataList());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
