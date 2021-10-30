package com.frabelovix.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frabelovix.wallet.entitty.Wallet;
import com.frabelovix.wallet.repository.WalletRepository;
import com.frabelovix.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	WalletRepository repository;

	@Override
	public Wallet save(Wallet w) {
		
		return repository.save(w);
	}
	
}
