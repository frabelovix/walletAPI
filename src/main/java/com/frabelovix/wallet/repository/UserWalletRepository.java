package com.frabelovix.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frabelovix.wallet.entitty.UserWallet;


public interface UserWalletRepository extends JpaRepository<UserWallet,Long> {

}
