package com.example.demo0.rabbit;

import javax.annotation.Resource;

public class RabbitProducer {
	@Resource
	private RabbitConfig rabbitConfig;

	public void send(String routingKey,String message){
		rabbitConfig.getRabbitTemplate().convertAndSend(routingKey, message);
	}
}
