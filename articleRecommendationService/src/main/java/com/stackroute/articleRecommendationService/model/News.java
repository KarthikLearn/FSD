package com.stackroute.articleRecommendationService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class News {
	
	@Id
	private String title;
	private String author;
	private String description;
	private String url;
	private String urlToImage;
	private String content;
	private int favouriteCount;
	
	public News() {
		
	}

	public News(String title, String author, String description, String url, String urlToImage, String content,int favouriteCount) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.url = url;
		this.urlToImage = urlToImage;
		this.content = content;
		this.favouriteCount = favouriteCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = "Content";
	}

	public int getFavouriteCount() {
		return favouriteCount;
	}

	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}

	public Object any() {
		// TODO Auto-generated method stub
		return null;
	}

}
