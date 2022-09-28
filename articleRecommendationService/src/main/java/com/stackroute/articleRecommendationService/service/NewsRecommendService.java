package com.stackroute.articleRecommendationService.service;

import java.util.List;

import com.stackroute.articleRecommendationService.dto.UserFavouriteNews;
import com.stackroute.articleRecommendationService.model.News;

public interface NewsRecommendService {

	public List<News> getAllRecommendArticle() throws Exception;
	
	public News saveFavouriteNews(UserFavouriteNews news) throws Exception;
}
