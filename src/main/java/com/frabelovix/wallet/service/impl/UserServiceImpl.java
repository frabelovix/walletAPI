package com.frabelovix.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frabelovix.wallet.entitty.User;
import com.frabelovix.wallet.repository.UserRepository;
import com.frabelovix.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;
	

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {

		return repository.findByEmailEquals(email);
	}

}
