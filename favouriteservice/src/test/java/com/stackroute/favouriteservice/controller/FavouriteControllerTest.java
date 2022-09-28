package com.stackroute.favouriteservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.exception.FavouriteAlreadyExistException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.model.UserNews;
import com.stackroute.favouriteservice.service.NewsFavouriteService;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FavouriteControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private FavouriteController favController;
	
	@MockBean
	private NewsFavouriteService newsService;
	
	private List newsList;
	
	@MockBean
	private News news;
	
	@MockBean
	private UserNews userNews;
	
	@Before
    public void setUp() throws Exception {
	 
	 MockitoAnnotations.initMocks(this);
	 mockMvc = MockMvcBuilders.standaloneSetup(favController).build();
	 
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
	
	@Test
    public void getAllFavNewsUserId() throws Exception {
        when(newsService.getAllFavouriteNews("Karthik11")).thenReturn(newsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favouriteservice/getAllFavourite/Karthik15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
	public void AddNewsFavList() throws Exception {
		when(newsService.addFavouriteNews(any(), eq(userNews.getUserId()))).thenReturn(userNews);
		mockMvc.perform(post("/api/v1/favouriteservice/addFavourite/{userId}", userNews.getUserId())
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
				.andExpect(status().isCreated()).andDo(print());

		verify(newsService, times(1)).addFavouriteNews(any(), eq(userNews.getUserId()));
		
	}
	
	@Test
	public void AddNewsFavListFail() throws Exception {
		when(newsService.addFavouriteNews(any(), eq(userNews.getUserId())))
				.thenThrow(FavouriteAlreadyExistException.class);
		mockMvc.perform(post("/api/v1/favouriteservice/addFavourite/{userId}", userNews.getUserId())
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
				.andExpect(status().isConflict()).andDo(print());

		verify(newsService, times(1)).addFavouriteNews(any(), eq(userNews.getUserId()));

	}
	
	private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	@After
	public void cleanData() {
		userNews = null;
	}
}
