package com.stackroute.userservice.controller;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController;
	
	@MockBean
	private UserService userService;
	
	private User user;
	
	 @Before
	    public void setUp() throws Exception {
		 
		 MockitoAnnotations.initMocks(this);
		 mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		 
		 user = new User();
		 user.setUserId("Karthik15");
		 user.setFirstName("Karthik");
		 user.setLastName("Partha");
		 user.setUserPassword("123456");
		 user.setUserEmail("karthik15@mail.com");
		 user.setUserAddedDate(new Date());
		 
	 }
	 
	 @Test
	    public void testRegisterUserSuccess() throws Exception {
		 Mockito.when(userService.registerUser(user)).thenReturn(true);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authService/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
	                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	 }
	
	 @Test
	    public void testLoginUser() throws Exception {


	        String userId = "Karthik15";
	        String password = "123456";


	        Mockito.when(userService.registerUser(user)).thenReturn(true);
	        Mockito.when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authService/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
	                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	    }
	
	
	 
	 private static String jsonToString(final Object obj) throws JsonProcessingException {
	        String result;
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            final String jsonContent = mapper.writeValueAsString(obj);
	            result = jsonContent;
	        } catch (JsonProcessingException e) {
	            result = "Json processing error";
	        }
	        return result;
	    }

}
