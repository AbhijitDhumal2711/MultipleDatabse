package com.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.user.model.User;
import com.ashokit.user.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		
		return repository.save(user);
	}
	
}
