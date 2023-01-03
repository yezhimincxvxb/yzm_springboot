package com.yzm.mongodb.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.yzm.mongodb.entity.Thing;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class DemoController {

    private final MongoTemplate mongoTemplate;
    private final String firstCollection = "thing";

    public DemoController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/createCollection")
    public void createCollection() {
        // 判断集合是否存在
        System.out.println(mongoTemplate.collectionExists(Thing.class));
        System.out.println(mongoTemplate.collectionExists(firstCollection));

        CollectionOptions options = CollectionOptions.empty()
                // 创建固定大小的集合，须指定size
                .capped()
                // 集合内存大小，单位字节，这里设置10M；当内存达到限制时，第一个插入的文档会被移除
                .size(10 * 1024 * 1024)
                // 集合最大文档数，这里设置1000个；当文档数达到限制时，第一个插入的文档会被移除
                .maxDocuments(1000);
        MongoCollection<Document> collection = mongoTemplate.createCollection(Thing.class, options);
        System.out.println("collection = " + collection);
    }

    @GetMapping("/insert")
    public void insert() {
        List<Thing> list = new ArrayList<>();
        list.add(Thing.builder().name("铅笔").price(2.00).left(100).build());
        list.add(Thing.builder().name("钢笔").price(8.00).left(5).build());
        list.add(Thing.builder().name("毛笔").price(5.50).left(20).build());
        // 批量插入
        Collection<Thing> things = mongoTemplate.insertAll(list);
        things.forEach(System.out::println);
        //mongoTemplate.insert(list, Thing.class);
        //mongoTemplate.insert(list, firstCollection);
        //mongoTemplate.insert(Thing.class).all(list);
        //mongoTemplate.insert(Thing.class).bulk(list);

        // 单条插入
        Thing thing = Thing.builder().name("圆珠笔").price(5.0).left(50).build();
        System.out.println(mongoTemplate.insert(thing));
        //mongoTemplate.insert(thing, firstCollection);
        //mongoTemplate.insert(Thing.class).one(thing);
    }

    @GetMapping("/save")
    public void save() {
        // 单条插入，不同于insert，save如果主键ID已存在则是修改操作，而insert则会报错
        Thing thing = Thing.builder().name("粉笔").price(1.0).left(200).build();
        System.out.println(mongoTemplate.save(thing));
        //mongoTemplate.save(thing, firstCollection);
    }

    @GetMapping("/findAll")
    public void findAll() {
        // 查询所有
        List<Thing> things = mongoTemplate.findAll(Thing.class);
        //mongoTemplate.findAll(Thing.class, firstCollection);
        things.forEach(System.out::println);
    }

    @GetMapping("/findById")
    public void findById(Object id) {
        // 根据ID查询
        Thing thing = mongoTemplate.findById(id, Thing.class);
        //mongoTemplate.findById(id, Thing.class,firstCollection);
        System.out.println("thing = " + thing);
    }

    @GetMapping("/findOne")
    public void findOne(String name) {
        // 根据条件返回查询结果集的第一个
        Query query = new Query(Criteria.where("name").is(name));
        Thing thing = mongoTemplate.findOne(query, Thing.class);
        //mongoTemplate.findOne(query, Thing.class,firstCollection);
        System.out.println("thing = " + thing);
    }

    @GetMapping("/findByWhere")
    public void findByWhere(String name) {
        // 根据条件返回查询结果集
        Query query = new Query(Criteria.where("name").is(name));
        List<Thing> things = mongoTemplate.find(query, Thing.class);
        //mongoTemplate.find(query, Thing.class,firstCollection);
        things.forEach(System.out::println);
    }

    @GetMapping("/updateFirst")
    public void updateFirst(String oldName, String newName) {
        // 单条修改
        Query query = new Query(Criteria.where("name").is(oldName));
        Update update = Update.update("name", newName);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Thing.class);
        //mongoTemplate.update(Thing.class).matching(query).apply(update).first();
        System.out.println("result = " + result);


//        FindAndModifyOptions options = FindAndModifyOptions.options().upsert(true);
//        mongoTemplate.findAndModify(query, update, options, Thing.class);

        FindAndReplaceOptions replaceOptions = FindAndReplaceOptions.empty().upsert();
        mongoTemplate.findAndReplace(query, new Thing(), replaceOptions);

    }

    @GetMapping("/updateMulti")
    public void updateMulti(int num1, int num2, String name) {
        // 批量修改
        Query query = new Query(Criteria.where("age").gte(num1).lte(num2));
        Update update = Update.update("name", name);
        UpdateResult result = mongoTemplate.updateMulti(query, update, Thing.class);
        //mongoTemplate.update(Thing.class).matching(query).apply(update).all();
        System.out.println("result = " + result);
    }

    @GetMapping("/upsert")
    public void upsert(int num1, int num2, String name) {
        // 存在查询文档则修改，否则插入文档
        Query query = new Query(Criteria.where("age").gte(num1).lte(num2));
        Update update = Update.update("name", name);
        UpdateResult result = mongoTemplate.upsert(query, update, Thing.class);
        //mongoTemplate.update(Thing.class).matching(query).apply(update).upsert();
        System.out.println("result = " + result);
    }

    @GetMapping("/remove")
    public void remove(String name) {
        // 移除文档
        Query query = new Query(Criteria.where("age").is(name));
        DeleteResult result = mongoTemplate.remove(query, Thing.class);
        System.out.println("result = " + result);
//        mongoTemplate.findAndRemove(query, Thing.class);
//        mongoTemplate.findAllAndRemove(query, Thing.class);
//        mongoTemplate.remove(Thing.class).matching(query).one();
    }

    @GetMapping("/page")
    public void page() {
        // 分页查询
        Query query = new Query()
                .skip(1)
                .limit(5)
                //.with(Sort.by(Sort.Order.asc("age")))
                .with(Sort.sort(Thing.class).by(Thing::getPrice).ascending());
        List<Thing> list = mongoTemplate.find(query, Thing.class);
        list.forEach(System.out::println);
    }


}
