package com.zhibo.org.zhibo.mapper.article;

import com.zhibo.org.zhibo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

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
    List<Article> getArticle(HashMap<String,Integer> condition);
}
