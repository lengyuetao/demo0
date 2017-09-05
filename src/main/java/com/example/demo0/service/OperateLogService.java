package com.example.demo0.service;

import com.example.demo0.enity.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2017/8/25.
 */
public interface OperateLogService{

    void insert(OperateLog operateLog);

    void delete(int id);

    void update(OperateLog operateLog);

    OperateLog findOperateLog(int id);

    List<OperateLog> fingOperateLogList();
}
