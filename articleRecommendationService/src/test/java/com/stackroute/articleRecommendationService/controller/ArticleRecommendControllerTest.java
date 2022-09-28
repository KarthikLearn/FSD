package com.stackroute.articleRecommendationService.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.articleRecommendationService.model.News;
import com.stackroute.articleRecommendationService.service.NewsRecommendService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ArticleRecommendControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NewsRecommendService newsService;
	
	@InjectMocks
	private ArticleRecommendController recommendController;
	
	private News news;
	
	List<News> allRecommendNews;
	
	
	@Before
	public void setup() {
		
		allRecommendNews = new ArrayList();
		news = new News();
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(recommendController).build();
		
		 news.setAuthor("Cointelegraph By William Suberg");
		 news.setContent("Bitcoin (BTC) briefly fell back below $7,200 on Dec. 14 as bearish sentiment continued to pervade cryptocurrency markets.\\r\\nCryptocurrency market daily overview.");
		 news.setDescription("The inventor of Bollinger Bands warns his price instrument shows Bitcoin and other cryptocurrencies are facing an imminent return to volatile conditions");
		 news.setTitle("Bitcoin news");
		 news.setUrl("https://cointelegraph.com/news/bitcoin-price-must-hit-73k-to-avoid-bearish-bollinger-band-squeeze");
		 news.setUrlToImage("https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9hNzY5OGE2ZGYzNTRmNmI2NmM0NTkyZjAxYTZjMTk0Ni5qcGc=.jpg");
		 
		 news.setFavouriteCount(1);		
		
	}
	
	@Test
	public void getAllRecommendList() throws Exception {
		when(newsService.getAllRecommendArticle()).thenReturn(allRecommendNews);
		mockMvc.perform(get("/api/v1/recommendservice/getRecommendList").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(allRecommendNews))).andExpect(status().isOk()).andDo(print());

		verify(newsService, times(1)).getAllRecommendArticle();

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
		news = null;
	}
	
}
