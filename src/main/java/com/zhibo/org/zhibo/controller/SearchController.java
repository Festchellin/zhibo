package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.service.search.SearchArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LS
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @RequestMapping("/article")
    public Object findArticleByKey( String keyWord){
        Map<String,Object> responseMap = new HashMap<>(3);
        Map<String,Object> dataMap = new HashMap<>(3);

        SearchArticleService articleService = new SearchArticleService();

        List<Article> search = null;
        try {
            search = articleService.search(keyWord, 1, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (Article pojo : search) {
//            System.out.println(pojo.getId()+" : "+pojo.getTitle()+" ： "+pojo.getAuthor());
//            System.out.println(pojo.getContent());
//            System.out.println("---------------------------------------------------------");
//
//        }

        dataMap.put("articleList",search);

        responseMap.put("error_code","200");
        responseMap.put("message","查询成功！！");
        responseMap.put("data",dataMap);

        return responseMap;
    }

}
