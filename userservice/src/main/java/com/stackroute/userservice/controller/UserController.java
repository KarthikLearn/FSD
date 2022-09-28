package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/authService")
public class UserController {
	
	@Autowired
	private UserService userService;
	private ResponseEntity<?> responseEntity;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "Login the User", tags = "Login User")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success | OK"),
			@ApiResponse(code = 401, message = "Un Authorized"), @ApiResponse(code = 403, message = "forbidden"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		String jwtToken = "";
		Map<String, String> map = new HashMap<>();
		try {
			String username = user.getUserId();
			String password = user.getUserPassword();
			if (username == null || password == null) {
				throw new ServletException("Please provide proper username and password");
			}
			User fetchUser = userService.findByUserIdAndPassword(username, password);
			if(fetchUser!=null) {
				jwtToken = generateToken(username, password);
				map.clear();
				map.put("message", "Login Success");
				map.put("token", jwtToken);
				responseEntity = new ResponseEntity<Object>(map, HttpStatus.OK);
			}
			else {
				throw new ServletException("Invalid Credentials");
			}
			
		} catch (Exception e) {
			map.clear();
			map.put("message", "Please enter valid UserId and Password");
			map.put("token", null);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;
	}
	
	@ApiOperation(value = "Save the user Details", tags = "Save the user Details")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success | OK"),
			@ApiResponse(code = 401, message = "Un Authorized"), @ApiResponse(code = 403, message = "forbidden"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	@PostMapping("/register")
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> saveUserDetails(@RequestBody User user) throws UserAlreadyExistsException {
		try {
			boolean isCreated = userService.registerUser(user);
			responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException exception) {
			responseEntity = new ResponseEntity<>("Same User already exists", HttpStatus.CONFLICT);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	public String generateToken(String username, String password) {
		
		String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS256, "userauthkey").compact();

		return jwtToken;
		
	}

}
