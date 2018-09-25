package com.zhibo.org.zhibo.service.search;

import com.zhibo.org.zhibo.entity.Article;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author LS
 */
@Service
public class SearchDao {
    private HttpSolrClient solrClient;

    public SearchDao(HttpSolrClient solrClient){

        this.solrClient = solrClient;
    }

    public SearchDao() {
    }

    public List<Article> search(SolrQuery query) throws Exception{
        //直接执行查询
        QueryResponse response = solrClient.query(query);
        //获取结果集
        SolrDocumentList results = response.getResults();
        //定义一个集合
        List<Article> articleList = new ArrayList<>();

        //取高亮
        Map<String,Map<String,List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument solrDocument : results) {
            //将solrDocument的属性，一个个的设置到pojo中
            Article pojo = new Article();
            pojo.setId(solrDocument.get("id").toString());


            List<String> list = highlighting.get(solrDocument.get("id")).get("title");
            String gaoliangStr = "";
            if (null != list && list.size() > 0){
                //有高亮
                gaoliangStr = list.get(0);
            }else {
                gaoliangStr = solrDocument.get("title").toString();
            }

            List<String> list1 = highlighting.get(solrDocument.get("id")).get("content");
            String gaoliangStr1 = "";
            if (null != list1 && list1.size() > 0){
                //有高亮
                gaoliangStr1 = list1.get(0);
            }else {
                gaoliangStr1 = solrDocument.get("content").toString();
            }

            pojo.setTitle(gaoliangStr);
            pojo.setContent(gaoliangStr1);
            articleList.add(pojo);
        }


        return articleList;
    }
}
