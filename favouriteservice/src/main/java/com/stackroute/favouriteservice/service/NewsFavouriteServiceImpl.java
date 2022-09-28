package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.dto.UserFavouriteNews;
import com.stackroute.favouriteservice.exception.FavouriteAlreadyExistException;
import com.stackroute.favouriteservice.exception.FavouriteNotFoundException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.model.UserNews;
import com.stackroute.favouriteservice.rabbitmq.Producer;
import com.stackroute.favouriteservice.repository.NewsFavouriteRepository;

@Service
public class NewsFavouriteServiceImpl implements NewsFavouriteService  {
	
	private NewsFavouriteRepository favouriteRepo;
	public Producer producer;
	
	@Autowired
	public NewsFavouriteServiceImpl(NewsFavouriteRepository favouriteRepo,Producer producer) {
		this.favouriteRepo = favouriteRepo;
		this.producer = producer;
	}
	
	
	@Override
	public UserNews addFavouriteNews(News news, String userId) throws FavouriteAlreadyExistException {
		
		UserNews favouriteUser = favouriteRepo.findByUserId(userId);
		if(favouriteUser==null) {
			favouriteUser = new UserNews(userId,new ArrayList<News>());
		}
		List <News> favNewsList = favouriteUser.getNews();
		if(!favNewsList.isEmpty()) {
			for(News favNews :favNewsList) {
				if(favNews.getTitle().equalsIgnoreCase(news.getTitle())) {
					throw new FavouriteAlreadyExistException();
				}
			}
			
			favNewsList.add(news);
			favouriteUser.setNews(favNewsList);
			
			SendFavNewsMQ(news,"ADDFAV");
			
			favouriteRepo.save(favouriteUser);
		}
		else {
			favNewsList = new ArrayList<News>();
			favNewsList.add(news);
			favouriteUser.setNews(favNewsList);
			SendFavNewsMQ(news,"ADDFAV");
			favouriteRepo.save(favouriteUser);
			
		}
		return favouriteUser;
	}


	private void SendFavNewsMQ(News news,String action) {
		UserFavouriteNews userFavNews = new UserFavouriteNews();
		userFavNews.setAuthor(news.getAuthor());
		userFavNews.setContent(news.getContent());
		userFavNews.setDescription(news.getDescription());
		userFavNews.setTitle(news.getTitle());
		userFavNews.setUrl(news.getUrl());
		userFavNews.setUrlToImage(news.getUrlToImage());
		userFavNews.setAction(action);
		if(producer!=null) {
			producer.sendMessageToRabbitMQ(userFavNews);
		}
		
	}

	@Override
	public List<News> getAllFavouriteNews(String userId) throws Exception {
		
		UserNews favouriteNews = favouriteRepo.findByUserId(userId);
		if(favouriteNews!=null&&favouriteNews.getNews()!=null) {
			return favouriteNews.getNews();
		}
		return null;
	}


	@Override
	public UserNews removeFavouriteNews(News news, String userId) throws FavouriteNotFoundException {
		
		UserNews favouriteUser = favouriteRepo.findByUserId(userId);
		if(favouriteUser==null) {
			favouriteUser = new UserNews(userId,new ArrayList<News>());
		}
		List <News> favNewsList = favouriteUser.getNews();
		News removeNews = null;
		if(!favNewsList.isEmpty()) {
			for(News favNews :favNewsList) {
				if(favNews.getTitle().equalsIgnoreCase(news.getTitle())) {
					removeNews = favNews;
					
				}
			}
		}
		
		if(!favNewsList.isEmpty()&&removeNews==null) {
			throw new FavouriteNotFoundException();
		}
		else {
			favNewsList.remove(removeNews);
			favouriteUser.setNews(favNewsList);
			SendFavNewsMQ(news,"REMOVEFAV");
			favouriteRepo.save(favouriteUser);
		}
		return favouriteUser;
	}

}
