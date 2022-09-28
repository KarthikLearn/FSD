package com.stackroute.favouriteservice.service;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.favouriteservice.exception.FavouriteAlreadyExistException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.model.UserNews;
import com.stackroute.favouriteservice.repository.NewsFavouriteRepository;
import static org.mockito.ArgumentMatchers.any;

import junit.framework.Assert;

public class FavouriteServiceTest {
	
	private List newsList;
	
	@MockBean
	private News news;
	
	@MockBean
	private UserNews userNews;
	
	@Mock
	private NewsFavouriteRepository newsRepo;
	
	 @InjectMocks
	 private NewsFavouriteServiceImpl newsService;
	
	@Before
	public void setup() {
	
		MockitoAnnotations.initMocks(this);
		
		 userNews = new UserNews();
		 news = new News();
		 newsList = new ArrayList<>();
		 
		 news.setAuthor("Cointelegraph By William Suberg");
		 news.setContent("Bitcoin (BTC) briefly fell back below $7,200 on Dec. 14 as bearish sentiment continued to pervade cryptocurrency markets.\\r\\nCryptocurrency market daily overview.");
		 news.setDescription("The inventor of Bollinger Bands warns his price instrument shows Bitcoin and other cryptocurrencies are facing an imminent return to volatile conditions");
		 news.setTitle("Bitcoin Price Must Hit $7.3K to Avoid Bearish Bollinger Band ‘Squeeze’");
		 news.setUrl("https://cointelegraph.com/news/bitcoin-price-must-hit-73k-to-avoid-bearish-bollinger-band-squeeze");
		 news.setUrlToImage("https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9hNzY5OGE2ZGYzNTRmNmI2NmM0NTkyZjAxYTZjMTk0Ni5qcGc=.jpg");
		 newsList.add(news);
		 
		 userNews.setUserId("Karthik11");
		 userNews.setNews(newsList);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addFavNewsList() throws FavouriteAlreadyExistException{
        

        when(newsRepo.insert((UserNews) any())).thenReturn(userNews);
        UserNews fetchUser = newsService.addFavouriteNews(news, userNews.getUserId());
        Assert.assertEquals(fetchUser.getUserId(), userNews.getUserId());
        verify(newsRepo, times(1)).findByUserId(userNews.getUserId());
        verify(newsRepo, times(1)).save((UserNews) any());
    
    
	}
	
	@Test
	public void getAllFavNews() throws Exception {
		Mockito.when(newsRepo.findByUserId(userNews.getUserId())).thenReturn(userNews);
		List fetchedList = newsService.getAllFavouriteNews(userNews.getUserId());
		Assert.assertEquals(fetchedList, newsList);
		verify(newsRepo, times(1)).findByUserId(userNews.getUserId());

	}
	
	@After
	public void cleanData() {
		userNews = null;
	}

}
