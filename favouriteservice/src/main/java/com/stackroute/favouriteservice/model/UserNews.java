package com.stackroute.favouriteservice.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserNews {
	
	@Id
	 private String userId;
	 private List<News> news;
	 
	 public UserNews() {
		 
	 }

	public UserNews(String userId, List<News> news) {
		super();
		this.userId = userId;
		this.news = news;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}
	 
}
