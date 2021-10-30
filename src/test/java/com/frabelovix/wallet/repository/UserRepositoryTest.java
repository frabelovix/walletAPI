package com.frabelovix.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.frabelovix.wallet.entitty.User;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	private final String EMAIL = "teste@teste.com";

	@Autowired
	UserRepository repository;

	@Before
	public void setUp() {
		User u = new User();
		u.setName("teste");
		u.setPassword("123456");
		u.setEmail(EMAIL);

		repository.save(u);
	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	public void deveSalvarUser() {
		User u = new User();
		u.setName("teste");
		u.setPassword("123456");
		u.setEmail(EMAIL);

		User response = repository.save(u);

		assertNotNull(response);
	}

	@Test
	public void deveEncontrarEmail() {
		Optional<User> u = repository.findByEmailEquals(EMAIL);

		assertTrue(u.isPresent());
		assertEquals(u.get().getEmail(), EMAIL);
	}

}
