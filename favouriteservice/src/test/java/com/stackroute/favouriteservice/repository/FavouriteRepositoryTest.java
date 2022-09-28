package com.stackroute.favouriteservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.model.UserNews;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavouriteRepositoryTest {
	
	@Autowired
    private NewsFavouriteRepository newsRepo;
	
	private UserNews userNews;
	
	private News news;
	
	private List newsList;
	
	 @Before
	 public void setUp() throws Exception {
			
			MockitoAnnotations.initMocks(this);
			
			 userNews = new UserNews();
			 news = new News();
			 newsList = new ArrayList<>();
			 
			 news.setAuthor("Cointelegraph By William Suberg");
			 news.setContent("Bitcoin (BTC) briefly fell back below $7,200 on Dec. 14 as bearish sentiment continued to pervade cryptocurrency markets.\\r\\nCryptocurrency market daily overview.");
			 news.setDescription("The inventor of Bollinger Bands warns his price instrument shows Bitcoin and other cryptocurrencies are facing an imminent return to volatile conditions");
			 news.setTitle("Bitcoin");
			 news.setUrl("https://cointelegraph.com/news/bitcoin-price-must-hit-73k-to-avoid-bearish-bollinger-band-squeeze");
			 news.setUrlToImage("https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9hNzY5OGE2ZGYzNTRmNmI2NmM0NTkyZjAxYTZjMTk0Ni5qcGc=.jpg");
			 newsList.add(news);
			 
			 userNews.setUserId("Karthik18");
			 userNews.setNews(newsList);
			
		}

	 @Test
	    public void saveFavNews() {
		 newsRepo.insert(userNews);
	        List<News> favNews = newsRepo.findByUserId("Karthik18").getNews();
	        Assert.assertEquals(((News) newsList.get(0)).getTitle(), favNews.get(0).getTitle());
	    }

	 @After
		public void cleanData() {
			userNews = null;
		}

}
