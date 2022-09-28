package com.stackroute.articleRecommendationService.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.articleRecommendationService.dto.UserFavouriteNews;
import com.stackroute.articleRecommendationService.model.News;
import com.stackroute.articleRecommendationService.service.NewsRecommendService;

@Component
public class Consumer {
	
	@Autowired
	private NewsRecommendService newsService;
	@RabbitListener(queues = "FAVOURITE-NEWS-QUEUE")
	public void getFavouriteNews(UserFavouriteNews news) {
		 try {
			 News favNews = newsService.saveFavouriteNews(news);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
