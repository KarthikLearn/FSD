package com.stackroute.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

		return userRepository.findByUserIdAndUserPassword(userId, password);
	}

	@Override
	public boolean registerUser(User user) throws UserAlreadyExistsException {

		Optional<User> isExist = userRepository.findById(user.getUserId());
		if (isExist.isPresent()) {
			throw new UserAlreadyExistsException("Same User Id already exists");
		} else {
			userRepository.save(user);
		}

		return true;
	}

}
