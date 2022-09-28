package com.stackroute.favouriteservice.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.favouriteservice.dto.UserFavouriteNews;



@Component
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private DirectExchange directExchange;
	
	public void sendMessageToRabbitMQ(UserFavouriteNews userFavNews) {
		rabbitTemplate.convertAndSend(directExchange.getName(), "FAVOURITE-NEWS-ROUTING-KEY", userFavNews);
	}
	
}
