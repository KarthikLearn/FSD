package com.stackroute.articleRecommendationService.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.articleRecommendationService.dto.UserFavouriteNews;
import com.stackroute.articleRecommendationService.model.News;
import com.stackroute.articleRecommendationService.repository.ArticleRecommendRepository;

@Service
public class NewsRecommendServiceImpl implements NewsRecommendService {
	
	private ArticleRecommendRepository recommendRepo;
	
	@Autowired
	public NewsRecommendServiceImpl(ArticleRecommendRepository recommendRepo) {
		this.recommendRepo = recommendRepo;
	}
	
	@Override
	public List<News> getAllRecommendArticle() throws Exception {
		
		List<News> recommendList = recommendRepo.findAll();
		return recommendList;
	}

	@Override
	public News saveFavouriteNews(UserFavouriteNews news) throws Exception {
		int favouriteCount = 0;
		News favNews  = recommendRepo.findBytitle(news.getTitle());
		if(favNews!=null) {
			if(news.getAction().equalsIgnoreCase("ADDFAV")) {
				favouriteCount = favNews.getFavouriteCount()+1;
				favNews.setFavouriteCount(favouriteCount);
				return recommendRepo.save(favNews);
			}
			else {
				favouriteCount = favNews.getFavouriteCount()-1;
				if(favouriteCount>0) {
					return recommendRepo.save(favNews);
				}
				else {
					 recommendRepo.delete(favNews);
				}
			}
			return favNews;
		}
		else {
			if(news.getAction()!=null &&news.getAction().equalsIgnoreCase("ADDFAV")) {
			News updateNews = new News();
			updateNews.setTitle(news.getTitle());
			updateNews.setAuthor(news.getAuthor());
			updateNews.setContent(news.getContent());
			updateNews.setDescription(news.getDescription());
			updateNews.setUrl(news.getUrl());
			updateNews.setUrlToImage(news.getUrlToImage());
			updateNews.setFavouriteCount(1);
			return recommendRepo.save(updateNews);
			}
		}
		return null;
	}

}
