package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liaoxin.bpo.ArticleBPO;
import com.liaoxin.client.UserClient;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.controller.BaseController;
import com.liaoxin.common.service.RedisCacheService;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.Label;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.domain.vo.UserArticleVO;
import com.liaoxin.service.ArticleService;
import com.liaoxin.service.ExportService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/article")
//@Api("文章模块请求接口")
public class ArticleController extends BaseController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleBPO articleBPO;
    @Autowired
    UserClient userClient;
    @Autowired
    ExportService exportService;
    @Autowired
    RedisCacheService redisCacheService;

    /**
     * 分页查询文章信息
     * @param pageNum   分页页数
     * @param pageSize  分页大小
     * @param keyword   关键字
     */
    @GetMapping("")
    public ResultBean articlePageBean(@RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize,
                                      @RequestParam(value = "userId", required = false) Long userId,
                                      @RequestParam(value = "keyword", required = false) String keyword){
        Page<ArticleVo> articleVoPage = articleService.selectArticleList(new PageDTO(pageNum,pageSize), keyword, userId);
        return ResultBean.success(articleVoPage);
    }

    /**
     * 按热度查询文章信息
     * @param pageNum   分页页数
     * @param pageSize  分页大小
     */
    @GetMapping("hot")
    public ResultBean findHotArticle(@RequestParam(value = "order", defaultValue = "1") String order,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize){
        Page<Article> articleVoPage = articleService.selectHotArticle(new PageDTO(pageNum,pageSize), order);
        return ResultBean.success(articleVoPage);
    }

    @GetMapping("/articleVoList")
    public ResultBean articleVoPage(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize){
        Page<ArticleVo> articleVoPage = articleBPO.articleVoList(new PageDTO(pageNum,pageSize));
        return ResultBean.success(articleVoPage);
    }

    /**
     * 查询文章详情
     * @param id    文章主键
     */
    @GetMapping("{id}")
    public ResultBean findArticle(@PathVariable("id") Long id){
        ArticleVo result = articleService.selectArticle(id);
        return ResultBean.success("success",result);
    }

    /**
     * 分类查询文章列表
     * @param id    标签主键
     */
    @GetMapping("/label/{id}")
    public ResultBean<List<ArticleVo>> findArticleWithLabel(@PathVariable("id") Long id){
        List<ArticleVo> articleVoList = articleService.selectArticleWithLabel(id);
        return ResultBean.success(articleVoList);
    }


    /**
     * 查询用户文章信息
     * @param pageNum   分页页数
     * @param pageSize  分页大小
     */
    @GetMapping("/user/{uid}")
    public ResultBean userArticleList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                      HttpServletRequest request){
        //UserAccountVo userAccountVo = userInfo(request);
        PageDTO<Article> pageDTO = new PageDTO<>(pageNum,pageSize);
        Page<Article> result = articleService.page(pageDTO, new QueryWrapper<Article>().eq("user_id",1L));
        //Page<Article> result = articleService.page(pageDTO, new QueryWrapper<Article>().eq("user_id",userAccountVo.getId()));
        List<ArticleVo> articleVoList = new ArrayList<>();
        ArticleVo articleVo = new ArticleVo();
        for(Article article : result.getRecords()){
            BeanUtils.copyProperties(article,articleVo);
            articleVoList.add(articleVo);
        }
        ResultBean<UmsUser> resultBean = userClient.findUserById(1L);
        UmsUser umsUser = resultBean.getData();
        UserArticleVO userArticleVO = new UserArticleVO();
        BeanUtils.copyProperties(umsUser,userArticleVO);
        userArticleVO.setArticleVoList(articleVoList);
        return ResultBean.success(userArticleVO);
    }

    /**
     * 新增文章
     * @param articleVo
     */
    @PostMapping("")
    public ResultBean insertArticle(HttpServletRequest request, @RequestBody ArticleVo articleVo){
        Assert.notNull(articleVo.getTitle(),"文章标题不能为空");
        Assert.notNull(articleVo.getContent(), "文章内容不能为空");
        Map userMap = userInfo(request);
        articleVo.setUserId(Long.valueOf(String.valueOf(userMap.get("id"))));
        articleBPO.insertArticleBPO(articleVo);
        return ResultBean.success();
    }

    /**
     * 更新文章信息
     * @param articleVo 文章试图类
     */
    @PutMapping("")
    public ResultBean updateArticle(@RequestBody ArticleVo articleVo){
        articleBPO.updateArticleBPO(articleVo);
        return ResultBean.success();
    }

    /**
     * 导出Excel表格
     * @param filename  文件名称
     */
    @GetMapping("exportExcel")
    public void exportExcel(@RequestParam("filename") String filename, HttpServletResponse response){
        Map<String,String> paramMap = new HashMap();
        paramMap.put("filename", filename);
        exportService.exportExcel(paramMap,response);
    }

    /**
     * 删除文章
     * @param userId    用户主键
     * @param articleIds 文章主键列表
     */
    @DeleteMapping("")
    public ResultBean deleteArticle(@RequestParam("userId") Long userId,
                                    @RequestParam("articleIds") String articleIds){
        List<String> articleIdList = Arrays.asList(articleIds.split(","));
        articleBPO.deleteArticle(userId, articleIdList);
        return ResultBean.success();
    }

}
