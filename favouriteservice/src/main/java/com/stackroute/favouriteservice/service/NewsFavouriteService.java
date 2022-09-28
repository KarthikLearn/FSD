package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.exception.FavouriteAlreadyExistException;
import com.stackroute.favouriteservice.exception.FavouriteNotFoundException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.model.UserNews;

public interface NewsFavouriteService {

	public UserNews addFavouriteNews(News news, String userId) throws FavouriteAlreadyExistException;
	
	public UserNews removeFavouriteNews(News news, String userId) throws FavouriteNotFoundException;

	public List<News> getAllFavouriteNews(String userId ) throws Exception;
}
