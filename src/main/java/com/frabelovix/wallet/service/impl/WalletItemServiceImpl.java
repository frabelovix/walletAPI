package com.frabelovix.wallet.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.frabelovix.wallet.entitty.WalletItem;
import com.frabelovix.wallet.repository.WalletItemRepository;
import com.frabelovix.wallet.service.WalletItemService;
import com.frabelovix.wallet.util.enums.TypeEnum;

@Service
public class WalletItemServiceImpl implements WalletItemService {
	@Autowired
	WalletItemRepository repository;
	
	@Value("${pagination.items_per_page}")
	private int itemPerPage;

	@Override
	public WalletItem save(WalletItem wi) {
		
		return repository.save(wi);
	}

	@Override
	public Page<WalletItem> FindBetweenDates(Long wallet, Date start, Date end, int page) {

		PageRequest pg = PageRequest.of(page, itemPerPage);

		return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, start, end, pg);
	}

	@Override
	public List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type) {
		
		return repository.findByWalletIdAndType(wallet, type);
	}

	@Override
	public BigDecimal sumByWalletId(Long wallet) {
		
		return repository.sumByWalletId(wallet);
	}
	
	

}