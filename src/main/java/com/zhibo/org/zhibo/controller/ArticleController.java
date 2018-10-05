package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.service.article.ArticleService;
import com.zhibo.org.zhibo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
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
        List<Article> articleList;
            articleList = articleService.selectArticleByRand(pageCount);
        System.out.println("长度："+articleList.size());
        dataMap.put("articleList",articleList);

        responseMap.put("error_code","1");
        responseMap.put("message","查询成功！！");
        responseMap.put("data",dataMap);

        return responseMap;
    }

    /**
     * 分页查询用户的文章
     * @param userId 用户唯一标识ID
     * @param pageNum 当前页
     * @param pageSize 每页显示的条数
     * @return 查询到的文章集合
     */
    @GetMapping("/{userId}")
    public Object getArticleByUserId(@PathVariable Integer userId,Integer pageNum,Integer pageSize){
        Map map;
        if (userId == null){
            map = ResponseUtil.loadResponseWithoutData("-1","传递的参数为空！！");
            return map;
        }

        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;

        List<Article> articleList = articleService.getArticleByUserId(userId, pageNum, pageSize);

        Map<String,List<Article>> dateMap = new HashMap<>(1);
        dateMap.put("文章列表",articleList);
        map = ResponseUtil.loadResponseWithData("1","查询成功！！",dateMap);
        return map;

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
        System.out.println(article);
        articleService.createArticle(article);
        map = ResponseUtil.loadResponseWithoutData("1","文章创建成功");
        return map;
    }
}
