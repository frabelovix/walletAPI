package com.frabelovix.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frabelovix.wallet.entitty.WalletItem;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long>{

}
