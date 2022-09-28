package com.stackroute.articleRecommendationService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.articleRecommendationService.model.News;

@Repository
public interface ArticleRecommendRepository extends MongoRepository<News, Integer> {
	
	public News findBytitle(String title);

}
