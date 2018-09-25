package com.zhibo.org.zhibo.service.search;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.service.article.ArticleService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchArticleService {
    @Autowired
    public ArticleService articleService;

    String url = "http://192.168.112.131:8983/solr/newCode";

    private HttpSolrClient solrClient;

    //模拟从数据库中查询出来的信息

    private List<Article> articleList;


    public SearchArticleService(){
        this.solrClient = new HttpSolrClient.Builder(url).build();
    }

    public boolean importAllArticle() throws Exception{
        Integer pageCount = 10;

        int articleCount = articleService.getArticleCount();
        for(int i = 0;i < articleCount;i=i+pageCount){
            articleList = articleService.getArticle(i, pageCount);
            if (articleList.size() == 0){
                //没有数据返回
                break;
            }
            System.out.println("List长度："+ this.articleList.size());
            for (Article pojo : this.articleList) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id",pojo.getId());
                document.addField("title",pojo.getTitle());
                document.addField("author",pojo.getAuthor());
                document.addField("content",pojo.getContent());

                solrClient.add(document);
            }
            //提交
            solrClient.commit();

        }
        return true;
    }

    public List<Article> search(String queryString,int page,int rows) throws Exception{
        //创建solrQuery对象
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);

        //设置分页条件
        query.setStart((page-1)*rows);
        query.setRows(rows);

        //指定默认搜索域
//        query.set("df","title");

        //设置高亮
        query.setHighlight(true);
        query.addHighlightField("title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        query.addHighlightField("content");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        //执行查询
        SearchDao searchDao = new SearchDao(solrClient);
        List<Article> pojoList = searchDao.search(query);


        return pojoList;
    }
}
