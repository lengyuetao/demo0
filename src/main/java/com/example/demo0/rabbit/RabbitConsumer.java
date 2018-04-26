package com.example.demo0.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

public class RabbitConsumer implements ChannelAwareMessageListener{

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
	  try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println(new String(message.getBody(),"utf-8"));
           
        } catch (Throwable e) {
           System.out.println(e.getMessage());
        } 
		
	}

}
