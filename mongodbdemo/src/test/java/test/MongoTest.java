package test;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class MongoTest {
    @Test
    public void test01(){
        //创建连接
        MongoClient client = new MongoClient("127.0.0.1:8989");
        //打开数据库
        MongoDatabase mongoDatabase = client.getDatabase("commentdb");
        //获取集合
        MongoCollection<Document> comment = mongoDatabase.getCollection("comment");


        //查询
        FindIterable<Document> documents = comment.find();

        //查询记录获取文档集合
        for (Document document : documents){
            System.out.println("_id:"+document.get("_id"));
            System.out.println("内容:"+document.get("content"));
            System.out.println("用户ID:"+document.get("userid"));
            System.out.println("点赞数:"+document.get("thumbup"));
        }
        //关闭连接
        client.close();

    }

    private MongoClient client;
    private MongoCollection<Document> comment;

    @Before
    public void init(){
        //创建连接
        client = new MongoClient("127.0.0.1:8989");
        //打开数据库
        MongoDatabase mongoDatabase = client.getDatabase("commentdb");
        //获取集合
        comment = mongoDatabase.getCollection("comment");
    }

    @After
    public void after(){
        client.close();
    }

    @Test
    public void test2(){
        //查询
        FindIterable<Document> documents = comment.find(new BasicDBObject("_id","2"));

        //查询记录获取文档集合
        for (Document document : documents){
            System.out.println("_id:"+document.get("_id"));
            System.out.println("内容:"+document.get("content"));
            System.out.println("用户ID:"+document.get("userid"));
            System.out.println("点赞数:"+document.get("thumbup"));
        }
    }

    /**
     * 新增
     */
    @Test
    public void add(){
        Map<String,Object> map = new HashMap<>(4);
        map.put("_id","6");
        map.put("content","很棒！");
        map.put("userid","6666");
        map.put("thumbup",123);

        Document document = new Document(map);

        comment.insertOne(document);
    }

    /**
     * 修改
     */
    @Test
    public void update(){
        //修改的条件
        Bson filter = new BasicDBObject("_id","6");
        //修改的数据
        Bson update = new BasicDBObject("$set",new Document("userid","8888"));

        comment.updateOne(filter,update);
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        Bson filter = new BasicDBObject("_id","6");

        comment.deleteOne(filter);
    }
}
