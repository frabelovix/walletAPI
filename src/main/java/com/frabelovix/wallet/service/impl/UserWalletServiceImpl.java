package com.frabelovix.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frabelovix.wallet.entitty.UserWallet;
import com.frabelovix.wallet.repository.UserWalletRepository;
import com.frabelovix.wallet.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService{
	
	@Autowired
	UserWalletRepository repository;

	@Override
	public UserWallet save(UserWallet uw) {

		return repository.save(uw);
	}
	
	@Override
	public Optional<UserWallet> findByUsersIdAndWalletId(Long user, Long wallet) {
		return repository.findByUsersIdAndWalletId(user, wallet);
	}
	

}
