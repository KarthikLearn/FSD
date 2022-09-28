package com.stackroute.articleRecommendationService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.articleRecommendationService.service.NewsRecommendService;
import com.stackroute.articleRecommendationService.model.News;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/recommendservice")
public class ArticleRecommendController {
	
	private ResponseEntity responseEntity;
	
	private NewsRecommendService recommendService;
	
	@Autowired
	public ArticleRecommendController(NewsRecommendService recommendService) {
		this.recommendService = recommendService;
	}
	
	@GetMapping("/getRecommendList")
	public ResponseEntity<?> getRecommendArticleList() {

		try {
			List<News> articleList = recommendService.getAllRecommendArticle();
			responseEntity = new ResponseEntity(articleList, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;

	}


}
