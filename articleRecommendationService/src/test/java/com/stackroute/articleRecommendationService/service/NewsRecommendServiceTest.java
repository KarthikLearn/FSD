package com.stackroute.articleRecommendationService.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.articleRecommendationService.dto.UserFavouriteNews;
import com.stackroute.articleRecommendationService.model.News;
import com.stackroute.articleRecommendationService.repository.ArticleRecommendRepository;


import junit.framework.Assert;


@RunWith(SpringRunner.class)
@DataMongoTest
public class NewsRecommendServiceTest {
	
	 @Mock
	private ArticleRecommendRepository articleRepo;
	
	@InjectMocks
	private NewsRecommendServiceImpl newsRecommendService;
	
	 News news;
	 
	 UserFavouriteNews userFavNews;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		news = new News();
		userFavNews = new UserFavouriteNews();
		userFavNews.setAuthor("Cointelegraph By William Suberg");
		userFavNews.setContent("Bitcoin (BTC) briefly fell back below $7,200 on Dec. 14 as bearish sentiment continued to pervade cryptocurrency markets.\\r\\nCryptocurrency market daily overview.");
		userFavNews.setDescription("The inventor of Bollinger Bands warns his price instrument shows Bitcoin and other cryptocurrencies are facing an imminent return to volatile conditions");
		userFavNews.setTitle("Bitcoin Flash 12");
		userFavNews.setUrl("https://cointelegraph.com/news/bitcoin-price-must-hit-73k-to-avoid-bearish-bollinger-band-squeeze");
		userFavNews.setUrlToImage("https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9hNzY5OGE2ZGYzNTRmNmI2NmM0NTkyZjAxYTZjMTk0Ni5qcGc=.jpg");
		userFavNews.setAction("ADDFAV"); 
		
		userFavNews = new UserFavouriteNews();
		news.setAuthor("Cointelegraph By William Suberg");
		news.setContent("Bitcoin (BTC) briefly fell back below $7,200 on Dec. 14 as bearish sentiment continued to pervade cryptocurrency markets.\\r\\nCryptocurrency market daily overview.");
		news.setDescription("The inventor of Bollinger Bands warns his price instrument shows Bitcoin and other cryptocurrencies are facing an imminent return to volatile conditions");
		news.setTitle("Bitcoin Flash 12");
		news.setUrl("https://cointelegraph.com/news/bitcoin-price-must-hit-73k-to-avoid-bearish-bollinger-band-squeeze");
		news.setUrlToImage("https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9hNzY5OGE2ZGYzNTRmNmI2NmM0NTkyZjAxYTZjMTk0Ni5qcGc=.jpg");
		news.setFavouriteCount(1);	
	}
	
	@Test
	public void testSaveFavouriteNews() throws Exception {
		
		 when(articleRepo.insert((News) any())).thenReturn(news);
		News saveNews = newsRecommendService.saveFavouriteNews(userFavNews);
		//Assert.assertEquals(saveNews, news);
	}
	
	@Test
	public void getAllRecommendNews() throws Exception{

		Mockito.when(articleRepo.save(news)).thenReturn(news);
		List<News> allrecommendNews =newsRecommendService.getAllRecommendArticle();
		verify(articleRepo, times(1)).findAll();
		
		
	}
	
	@After
	public void cleanData() {
		news = null;
	}

}
