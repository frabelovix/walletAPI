package com.frabelovix.wallet.service;

import java.util.Optional;

import com.frabelovix.wallet.entitty.User;

public interface UserService {
	
	User save(User user);
	
	Optional<User> findByEmail(String email);

}
