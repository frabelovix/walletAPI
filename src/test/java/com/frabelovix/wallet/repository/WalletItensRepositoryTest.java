package com.frabelovix.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.frabelovix.wallet.entitty.Wallet;
import com.frabelovix.wallet.entitty.WalletItem;
import com.frabelovix.wallet.util.enums.TypeEnum;

@SpringBootTest
@ActiveProfiles("test")
public class WalletItensRepositoryTest {

	private static final Long ID = 1L;
	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Jogo do bicho";
	private static final BigDecimal VALUE = BigDecimal.valueOf(47);
	private Long savedWalletItemId = null;
	private Long savedWalletId = null;

	@Autowired
	WalletItemRepository repository;
	@Autowired
	WalletRepository walletRepository;

	@Before
	public void setUp() {
		Wallet w = new Wallet();
		w.setName("Carteira Teste");
		w.setValue(BigDecimal.valueOf(250));
		walletRepository.save(w);

		WalletItem wi = new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
		repository.save(wi);

		savedWalletItemId = wi.getId();
		savedWalletId = w.getId();
	}

	@After
	public void tearDown() {
		repository.deleteAll();
		walletRepository.deleteAll();
	}

	@Test
	public void testSave() {
		Wallet w = new Wallet();
		w.setName("Carteira 1");
		w.setValue(BigDecimal.valueOf(470));
		walletRepository.save(w);

		WalletItem wi = new WalletItem(ID, w, DATE, TYPE, DESCRIPTION, VALUE);

		WalletItem response = repository.save(wi);
		assertNotNull(response);
		assertEquals(response.getDescription(), DESCRIPTION);

	}

	@Test
	public void testSaveInvalidWalletItem() {
		WalletItem wi = new WalletItem(null, null, DATE, null, DESCRIPTION, null);

		assertThrows(ConstraintViolationException.class, () -> {
			repository.save(wi);
		});
	}


	@Test
	public void testUpdate() {
		Wallet w = new Wallet();
		w.setName("Carteira Teste");
		w.setValue(BigDecimal.valueOf(250));
		walletRepository.save(w);

		WalletItem wi = new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
		repository.save(wi);

		savedWalletItemId = wi.getId();
		savedWalletId = w.getId();

		Optional<WalletItem> wupdate = repository.findById(savedWalletItemId);

		String description = "Descrição alterada";
		WalletItem changed = wupdate.get();
		changed.setDescription(description);

		repository.save(changed);

		Optional<WalletItem> newWalletItem = repository.findById(savedWalletItemId);

		assertEquals(description, newWalletItem.get().getDescription());

	}

	@Test
	public void deleteWalletItem() {

		Wallet w = new Wallet();
		w.setName("Carteira Teste");
		w.setValue(BigDecimal.valueOf(250));
		walletRepository.save(w);

		savedWalletId = w.getId();

		Optional<Wallet> wallet = walletRepository.findById(savedWalletId);
		WalletItem wi = new WalletItem(null, wallet.get(), DATE, TYPE, DESCRIPTION, VALUE);

		repository.save(wi);

		repository.deleteById(wi.getId());

		Optional<WalletItem> response = repository.findById(wi.getId());

		assertFalse(response.isPresent());

	}

	@Test
	public void testFindByType() {
		Wallet w = new Wallet();
		w.setName("Carteira Teste");
		w.setValue(BigDecimal.valueOf(250));
		walletRepository.save(w);
		savedWalletId = w.getId();

		WalletItem wi = new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
		repository.save(wi);
		
		
		List<WalletItem> response = repository.findByWalletIdAndType(savedWalletId, TYPE);

		assertEquals(response.size(), 1);
		assertEquals(response.get(0).getType(), TYPE);

	}

}
