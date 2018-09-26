package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.service.article.ArticleService;
import com.zhibo.org.zhibo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LS
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    public ArticleService articleService;

    /**
     * 随机查询一定条数的文章
     * @return
     */
    @GetMapping
    public Object findArticle(){
        Map<String,Object> responseMap = new HashMap<>(3);
        Map<String,Object> dataMap = new HashMap<>(3);

        //每次查询的条数
        int pageCount = 10;

        List<Article> articleList = articleService.selectArticleByRand(pageCount);
        System.out.println("长度："+articleList.size());
        dataMap.put("articleList",articleList);

        responseMap.put("error_code","1");
        responseMap.put("message","查询成功！！");
        responseMap.put("data",dataMap);

        return responseMap;
    }

    /**
     * 通过文章ID查询文章内容
     * @param articleId 文章ID
     * @return
     */
    @GetMapping("/{articleId}")
    public Object findArticleById(@PathVariable String articleId){
        Map<String,Object> responseMap = new HashMap<>(3);
        Map<String,Object> dataMap = new HashMap<>(3);

        Article article = articleService.selectArticleById(articleId);

        dataMap.put("article",article);

        responseMap.put("error_code","1");
        responseMap.put("message","查询成功！！");
        responseMap.put("data",dataMap);

        return responseMap;
    }

    @DeleteMapping("/{articleId}")
    public Object delectArticleById(@PathVariable String articleId){
        Map map;

        articleService.delectArticleById(articleId);
        map = ResponseUtil.loadResponseWithoutData("1","删除成功");

        return map;
    }

    @PutMapping
    public Object updateArticle(Article article){
        Map map;

        articleService.updateArticle(article);
        map = ResponseUtil.loadResponseWithoutData("1","修改成功");
        return map;
    }

    @PostMapping
    public Object createArticle(Article article){
        Map map;

        articleService.createArticle(article);
        map = ResponseUtil.loadResponseWithoutData("1","文章创建成功");
        return map;
    }
}
