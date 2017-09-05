package com.example.demo0.service.impl;

import com.example.demo0.enity.OperateLog;
import com.example.demo0.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2017/8/28.
 */
@Component
public class OperateLogImpl implements OperateLogService {
    @Autowired
    private  MongoTemplate mongoTemplate;

    @Override
    public void insert(OperateLog operateLog) {
        mongoTemplate.save(operateLog);
    }

    @Override
    public void delete(int id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,OperateLog.class);
    }

    @Override
    public void update(OperateLog operateLog) {
        Query query=new Query(Criteria.where("id").is(operateLog.getId()));
        Update update= new Update().set("operateName", operateLog.getOperateName())
                .set("descripes",operateLog.getDescripes())
                .set("content",operateLog.getContent())
                .set("addTime",operateLog.getAddTime());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,OperateLog.class);

    }

    @Override
    public OperateLog findOperateLog(int id) {
        Query query=new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query , OperateLog.class);
    }

    @Override
    public List<OperateLog> fingOperateLogList() {
        return mongoTemplate.findAll(OperateLog.class);
    }
}
