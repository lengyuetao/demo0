package com.example.demo0.listener;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by DELL on 2017/9/4.
 */
@Component
public class MsgConsumer {

    @KafkaListener(topics = {"test"})
    public void consumer(ConsumerRecord<?, ?> record){
        System.out.println(record.toString());
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();

            JSONObject json= (JSONObject) JSONObject.parse(message.toString());
            System.out.println("kafka发送的消息是:" + json.getString("content"));
        }
    }
}
