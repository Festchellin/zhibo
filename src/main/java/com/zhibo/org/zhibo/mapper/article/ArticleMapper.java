package com.zhibo.org.zhibo.mapper.article;

import com.zhibo.org.zhibo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 随机查询一定条数的文章
     * @param pageCount 指定查询文章的条数
     * @return 文章的List集合
     */
    List<Article> selectArticleByRand(int pageCount);

    /**
     * 根据文章ID查询文章内容
     * @param articleId 文章ID
     * @return 文章对象实体
     */
    Article selectArticleById(String articleId);

    /**
     * 根据文章ID删除文章
     * @param articleId 文章ID
     */
    void delectArticleById(String articleId);

    /**
     * 修改文章
     * @param article 待修改文章的实体
     */
    void updateArticle(Article article);

    /**
     * 创建文章
     */
    void createArticle(Article article);

    /**
     * 根据文章的Id查询文章的点赞和差评总数
     * @param articleId 文章ID
     * @return 文章类实体对象
     */
    Article findLikesAndDislikesByArticleId(String articleId);

    /**
     * 设置该文章的点赞总数和差评总数
     * @param article 文章实体对象
     */
    void setLikesAndDislikes(Article article);

    /**
     * 根据用户ID查询该用户的文章
     * @param dataMap 参数的Map集合
     * @return 文章集合
     */
    List<Article> getArticleByUserId(Map<String,Integer> dataMap);
}
