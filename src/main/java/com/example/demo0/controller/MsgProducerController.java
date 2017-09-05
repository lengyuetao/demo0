package com.example.demo0.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo0.enity.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by DELL on 2017/9/4.
 */
@RestController
public class MsgProducerController {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping(value="/send",method = RequestMethod.POST)
    public String sendMsg(@RequestParam(value="operateName")String operateName,
                          @RequestParam(value="descripes")String descripes,
                          @RequestParam(value="content")String content){

        OperateLog operateLog=new OperateLog();

        operateLog.setId((long) new Random().nextInt(1000));
        operateLog.setOperateName(operateName);
        operateLog.setDescripes(descripes);
        operateLog.setContent(content);
        operateLog.setAddTime(new Date());

        String uid= UUID.randomUUID().toString().replace("-","");
        kafkaTemplate.send("test",uid,JSONObject.toJSON(operateLog).toString());
        return "success";
    }
}
