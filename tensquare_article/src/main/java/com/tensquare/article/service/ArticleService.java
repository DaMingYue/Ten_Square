package com.tensquare.article.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.dao.IArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import until.IdWorker;


import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private IArticleDao IArticleDao;


    @Autowired
    private IdWorker idWorker;


    public List<Article> findAll(){
        return IArticleDao.selectList(null);
    }

    public Article findById(String id){
        return IArticleDao.selectById(id);
    }

    public void add(Article article) {
        article.setId(idWorker.nextId() + "");
        IArticleDao.insert(article);
    }

    public void update(Article article) {
        int i = (int)Math.random();
        if (i>5){
            //方法1
            IArticleDao.updateById(article);
        }else{
            //方法2
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq("id",article.getId());
            IArticleDao.update(article,wrapper);
        }
    }

    public void delete(String id) {
        IArticleDao.deleteById(id);
    }

    public Page search(Map map, int page, int size) {
        EntityWrapper wrapper = new EntityWrapper<Article>();
        Set<String> fieldSet = map.keySet();
        for (String field : fieldSet){
            //动态sql,例如<if test="null != field"> and field='xxx' </if>
            wrapper.eq(null != map.get(field),field,map.get(field));
        }

        Page page1 = new Page(page,size);
        List list = IArticleDao.selectPage(page1,wrapper);
        page1.setRecords(list);
        return page1;
    }
}
