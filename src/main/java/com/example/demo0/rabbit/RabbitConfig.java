package com.example.demo0.rabbit;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP;

@Configuration
public class RabbitConfig {

	//队列名称
	@Value("${rabbit.queueName}")
	private String queueName;
	/**
	 * 帐户名
	 */
	@Value("${rabbit.userName}")
	private String userName;
	/**
	 * 密码
	 */
	@Value("${rabbit.password}")
	private String password;
	/**
	 * ip地址
	 */
	@Value("${rabbit.host}")
	private String host;
	/**
	 * 交换机
	 */
	@Value("${rabbit.exchange}")
	private String exchange;
	/**
	 * 路由
	 */
	@Value("${rabbit.routingKey}")
	private String routingKey;
	/**
	 * 是否持久化
	 */
	@Value("${rabbit.durable}")
	private boolean durable;
	/**
	 * 仅创建者可以使用的私有队列，断开后自动删除
	 */
	@Value("${rabbit.exclusive}")
	private boolean exclusive;
	/**
	 * 当所有消费客户端连接断开后，是否自动删除队列
	 */
	@Value("${rabbit.autoDelete}")
	private boolean autoDelete;
	
	@Bean
    public ConnectionFactory connectionFactory() {  
	  CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);  
      connectionFactory.setUsername(userName);  
      connectionFactory.setPassword(password);  
      connectionFactory.setPort(AMQP.PROTOCOL.PORT);  
      return connectionFactory;  
    }
    
    @Bean
    public AmqpAdmin amqpAdmin() {  
        return new RabbitAdmin(connectionFactory());  
    }  
    
    @Bean
    public RabbitTemplate getRabbitTemplate(){
    	RabbitTemplate template=new RabbitTemplate(connectionFactory());
    	template.setExchange(exchange);
    	template.setRoutingKey(routingKey);
    	template.setQueue(queueName);
    	template.setMessageConverter(null);
    	template.setMessageConverter(new Jackson2JsonMessageConverter());
    	return template;
    }
    
	@Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(queueName);
        container.setMessageListener(new RabbitConsumer());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
	
	@Bean
    public Queue rabbitQueue() {  
        return new Queue(queueName,durable,exclusive,autoDelete);  
    }

	public String getQueueName() {
		return queueName;
	}


	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getExchange() {
		return exchange;
	}


	public void setExchange(String exchange) {
		this.exchange = exchange;
	}


	public String getRoutingKey() {
		return routingKey;
	}


	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}


	public boolean isDurable() {
		return durable;
	}


	public void setDurable(boolean durable) {
		this.durable = durable;
	}


	public boolean isExclusive() {
		return exclusive;
	}


	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}


	public boolean isAutoDelete() {
		return autoDelete;
	}


	public void setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
	}  
    
    public static void main(String[] args) {
		RabbitConfig config=new RabbitConfig();
		config.getRabbitTemplate();
		System.out.println("***");
		RabbitProducer send=new RabbitProducer();
		send.send("sss", "hello");
	}	
}
