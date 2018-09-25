package com.zhibo.org.zhibo.service.article;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.mapper.article.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
}
