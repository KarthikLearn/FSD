package com.stackroute.favouriteservice.rabbitmq;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("FAVOURITE-EXCHANGE");
	}
	
	@Bean
	public Queue favNewsQueue() {
		return new Queue("FAVOURITE-NEWS-QUEUE",false);
	}
	
	@Bean
	public Binding queueBinding(Queue favNewsQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(favNewsQueue()).to(directExchange()).with("FAVOURITE-NEWS-ROUTING-KEY");
	}
	
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFacory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFacory);
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
		return rabbitTemplate;
	}
	
}
