package com.tensquare.article.service;

import com.tensquare.article.dao.ICommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import until.IdWorker;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private IdWorker idWorker;

    @Resource
    private ICommentDao iCommentDao;

    @Resource
    private MongoTemplate mongoTemplate;

    public Comment findById(String id){
        return iCommentDao.findById(id).get();
    }

    public void save(Comment comment){
        String id = idWorker.nextId() + "";
        comment.set_id(id);

        //初始化数据
        comment.setPublishdate(new Date());
        comment.setThumbup(0);

        iCommentDao.save(comment);
    }

    public void update(Comment comment){
        iCommentDao.save(comment);
    }

    public void deleteById(String id){
        iCommentDao.deleteById(id);
    }

    public List<Comment> findAll() {
        return iCommentDao.findAll();
    }

    public List<Comment> findByArticleId(String articleId) {
        return iCommentDao.findByArticleid(articleId);
    }

    public void thumbup(String id){
        //查看评论
//        Comment comment = iCommentDao.findById(id).get();
//        //修改点赞数
//        comment.setThumbup(comment.getThumbup()+1);
//
//        iCommentDao.save(comment);
        //修改条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        //修改的数据
        Update update = new Update();
        //在原来的基础上加一
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"comment");
    }
}
