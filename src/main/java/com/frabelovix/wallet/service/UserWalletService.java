package com.frabelovix.wallet.service;

import java.util.Optional;

import com.frabelovix.wallet.entitty.UserWallet;

public interface UserWalletService {
	UserWallet save(UserWallet uw);
	
	Optional<UserWallet> findByUsersIdAndWalletId(Long user, Long wallet);
}
