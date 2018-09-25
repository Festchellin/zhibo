package com.zhibo.org.zhibo.service.article;

import com.zhibo.org.zhibo.entity.Article;

import java.util.HashMap;
import java.util.List;

public interface ArticleService {
    /**
     * 获取文章的总数
     * @return 文章总数
     */
    int getArticleCount();

    /**
     * 分页查询文章内容信息
     * @param start 开始查询的起始位置
     * @param pageCount 要查询多少条文章
     * @return 文章集合
     */
    List<Article> getArticle(Integer start,Integer pageCount);
}
