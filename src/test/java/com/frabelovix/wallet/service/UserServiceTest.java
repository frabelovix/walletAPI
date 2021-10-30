package com.frabelovix.wallet.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.frabelovix.wallet.entitty.User;
import com.frabelovix.wallet.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@MockBean
	UserRepository repository;

	@Autowired
	UserService service;

	@Before
	public void setUp() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new User()));

	}

	@Test
	public void testFindByEmail() {
		Optional<User> user = service.findByEmail("email@teste.com");
		
		assertTrue(user.isEmpty());

	}

}
