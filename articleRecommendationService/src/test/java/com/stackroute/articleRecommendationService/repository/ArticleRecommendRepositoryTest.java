package com.stackroute.articleRecommendationService.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.articleRecommendationService.model.News;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ArticleRecommendRepositoryTest {

	
	@Autowired
	ArticleRecommendRepository recommendRepo;
	
	public News news;
	
	@Before
	public void setup() {
		
		news = new News();
		
		 news.setAuthor("Cointelegraph By William Suberg");
		 news.setContent("Bitcoin (BTC) briefly fell back below $7,200 on Dec. 14 as bearish sentiment continued to pervade cryptocurrency markets.\\r\\nCryptocurrency market daily overview.");
		 news.setDescription("The inventor of Bollinger Bands warns his price instrument shows Bitcoin and other cryptocurrencies are facing an imminent return to volatile conditions");
		 news.setTitle("Bitcoin news");
		 news.setUrl("https://cointelegraph.com/news/bitcoin-price-must-hit-73k-to-avoid-bearish-bollinger-band-squeeze");
		 news.setUrlToImage("https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9hNzY5OGE2ZGYzNTRmNmI2NmM0NTkyZjAxYTZjMTk0Ni5qcGc=.jpg");
		 
		 news.setFavouriteCount(1);	
	}
	
	@Test
	public void saveFavNews() {
		
		int favCount = 0;
		News updateNewsCount;
		News fetchNews = recommendRepo.findBytitle(news.getTitle());
		if(fetchNews!=null) {
			favCount = fetchNews.getFavouriteCount()+1;
			fetchNews.setFavouriteCount(favCount);
			news = fetchNews;
			updateNewsCount = recommendRepo.save(news);
		}
		else {
			updateNewsCount = recommendRepo.insert(news);
		}
		Assert.assertEquals(updateNewsCount.getTitle(),news.getTitle());
		
	}
	
	@After
	public void cleanData() {
		news = null;
	}
}
