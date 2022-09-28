package com.stackroute.userservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

public class UserServiceTest {
	
	@Mock
    private UserRepository userRepository;

    private User user;
    @InjectMocks
    private UserServiceImpl userService;

    Optional<User> optional;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       
        user = new User();
		 user.setUserId("Karthik15");
		 user.setFirstName("Karthik");
		 user.setLastName("Partha");
		 user.setUserPassword("123456");
		 user.setUserEmail("karthik15@mail.com");
		 user.setUserAddedDate(new Date());
		 
		 optional = Optional.of(user);
		 
    }
		 	@Test
		    public void SaveUserSuccess() throws UserAlreadyExistsException {

		        Mockito.when(userRepository.save(user)).thenReturn(user);
		        boolean flag = userService.registerUser(user);
		        Assert.assertEquals("Cannot Register User", true, flag);

		    }


		    @Test(expected = UserAlreadyExistsException.class)
		    public void SaveUserFailure() throws UserAlreadyExistsException {

		        Mockito.when(userRepository.findById("Karthik15")).thenReturn(optional);
		        Mockito.when(userRepository.save(user)).thenReturn(user);
		        boolean flag = userService.registerUser(user);
		        Assert.assertEquals("Cannot Register User", true, flag);

		    }

		    @Test
		    public void testFindByUserIdAndPassword() throws UserNotFoundException {
		        Mockito.when(userRepository.findByUserIdAndUserPassword("Karthik15", "123456")).thenReturn(user);
		        User fetchedUser = userService.findByUserIdAndPassword("Karthik15", "123456");
		        Assert.assertEquals("Karthik15", fetchedUser.getUserId());
		    }
    }

