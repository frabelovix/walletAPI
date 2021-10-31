package com.frabelovix.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.frabelovix.wallet.entitty.WalletItem;
import com.frabelovix.wallet.util.enums.TypeEnum;

public interface WalletItemService {

	WalletItem save(WalletItem wi);
	
	Page<WalletItem> FindBetweenDates(Long wallet, Date start, Date end, int page );
	
	List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type);

	BigDecimal sumByWalletId(Long wallet);
}
