package com.frabelovix.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.frabelovix.wallet.entitty.Wallet;
import com.frabelovix.wallet.entitty.WalletItem;

@SpringBootTest
@ActiveProfiles("test")
public class WalletItensRepositoryTest {
	
	private static final Long ID = 1L;
	private static final Date DATE = new Date();
	private static final String TYPE = "EN";
	private static final String DESCRIPTION = "Jogo do bicho";
	private static final BigDecimal VALUE = BigDecimal.valueOf(47);

	@Autowired
	WalletItemRepository repository;
	@Autowired
	WalletRepository walletRepository;

	
	
	@Test
	public void testSave(){
	Wallet w = new Wallet();
	w.setName("Carteira 1");
	w.setValue(BigDecimal.valueOf(470));
	walletRepository.save(w);

	WalletItem wi = new WalletItem(ID, w, DATE, TYPE, DESCRIPTION, VALUE);

	WalletItem response = repository.save(wi);
	assertNotNull(response);
	assertEquals(response.getDescription(), DESCRIPTION);

	}

	

}
