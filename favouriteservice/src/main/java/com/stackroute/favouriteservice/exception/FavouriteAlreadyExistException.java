package com.stackroute.favouriteservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Favourite News already exists")
public class FavouriteAlreadyExistException extends Exception{

}
