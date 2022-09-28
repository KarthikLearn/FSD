package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.exception.FavouriteAlreadyExistException;
import com.stackroute.favouriteservice.exception.FavouriteNotFoundException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.model.UserNews;
import com.stackroute.favouriteservice.service.NewsFavouriteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/favouriteservice")
public class FavouriteController {
	
	@Autowired
	NewsFavouriteService newsFavService;
	
	public FavouriteController(NewsFavouriteService newsFavService) {
		this.newsFavService = newsFavService;
	}
	
	public FavouriteController() {
		super();
	}

	private ResponseEntity<?> responseEntity;
	
	
	  @ApiOperation(value = "Add Favourite News", tags = "Add Favourite News")	  
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success | OK"),
	  @ApiResponse(code = 401, message = "Un Authorized"), @ApiResponse(code = 403, message = "forbidden"), 
	  @ApiResponse(code = 404, message = "Resource Not Found"),
	  @ApiResponse(code = 500, message = "Internal Server Error") })	
	@PostMapping("/addFavourite/{userId}")
	public ResponseEntity<?> AddFavoriteNews(@RequestBody News news,
			@PathVariable String userId) throws FavouriteAlreadyExistException {

		try {
			UserNews fetchedUser = newsFavService.addFavouriteNews(news, userId);
			responseEntity = new ResponseEntity<UserNews>(fetchedUser, HttpStatus.CREATED);
		} catch (FavouriteAlreadyExistException e) {
			throw new FavouriteAlreadyExistException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}
	
	
	  @ApiOperation(value = "Get the user Favourite news details", tags ="Get the user Favourite news details")	  
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success | OK"),
	  @ApiResponse(code = 401, message = "Un Authorized"), @ApiResponse(code = 403,
	  message = "forbidden"), 
	  @ApiResponse(code = 404, message = "Resource Not Found"),
	  @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/getAllFavourite/{userId}")
	public ResponseEntity<?> getFavouriteList(@PathVariable String userId) throws Exception {
		try {
			List<News> favouriteList = newsFavService.getAllFavouriteNews(userId);
			responseEntity = new ResponseEntity(favouriteList, HttpStatus.OK);
		}
		catch(Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	  
	  
	  @ApiOperation(value = "Remove Favourite News", tags = "Remove Favourite News")	  
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success | OK"),
	  @ApiResponse(code = 401, message = "Un Authorized"), @ApiResponse(code = 403, message = "forbidden"), 
	  @ApiResponse(code = 404, message = "Resource Not Found"),
	  @ApiResponse(code = 500, message = "Internal Server Error") })	
	@DeleteMapping("/removeFavourite/{userId}")
	public ResponseEntity<?> removeFavouriteNews(@RequestBody News news,
			@PathVariable String userId) throws FavouriteNotFoundException {

		try {
			UserNews fetchedUser = newsFavService.removeFavouriteNews(news, userId);
			responseEntity = new ResponseEntity<List<News>>(fetchedUser.getNews(), HttpStatus.OK);
		} catch (FavouriteNotFoundException e) {
			throw new FavouriteNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}

}
