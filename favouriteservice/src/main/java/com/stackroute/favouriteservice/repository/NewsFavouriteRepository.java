package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.model.UserNews;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsFavouriteRepository extends MongoRepository<UserNews, String>  {

	public UserNews findByUserId(String userId);
}
