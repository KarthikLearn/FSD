package com.stackroute.userservice.repository;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {



    @Autowired
    private UserRepository userRepository;

    private User user;


    @Before
    public void setUp() throws Exception {
    	 user = new User();
		 user.setUserId("Karthik15");
		 user.setFirstName("Karthik");
		 user.setLastName("Partha");
		 user.setUserPassword("123456");
		 user.setUserEmail("karthik15@mail.com");
		 user.setUserAddedDate(new Date());
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
        userRepository.save(user);
        User object = userRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }

    @Test
    public void testLoginUserSuccess() {
        userRepository.save(user);
        User object = userRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }
}
