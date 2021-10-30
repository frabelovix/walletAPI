package com.frabelovix.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frabelovix.wallet.entitty.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
