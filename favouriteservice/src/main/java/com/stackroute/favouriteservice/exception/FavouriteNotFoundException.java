package com.stackroute.favouriteservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Favourite News Not Found")
public class FavouriteNotFoundException extends Exception{

}
