package com.zhibo.org.zhibo.service.article;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.mapper.article.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
/**
 * @author LS
 */
@Service
public class ArticleImpl implements ArticleService {
    @Autowired
    public ArticleMapper articleMapper;

    @Override
    public int getArticleCount() {
        return articleMapper.getArticleCount();
    }

    @Override
    public List<Article> getArticle(Integer start,Integer pageCount) {
        HashMap<String,Integer> conditionMap = new HashMap<>(2);
        conditionMap.put("start",start);
        conditionMap.put("pageCount",pageCount);
        return articleMapper.getArticle(conditionMap);
    }

    @Override
    public List<Article> selectArticleByRand(int pageCount) {
        List<Article> articleList = articleMapper.selectArticleByRand(pageCount);
        return articleList;
    }

    @Override
    public Article selectArticleById(String articleId) {
        Article article = articleMapper.selectArticleById(articleId);
        return article;
    }

    @Override
    public void delectArticleById(String articleId) {
        articleMapper.delectArticleById(articleId);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }
}
