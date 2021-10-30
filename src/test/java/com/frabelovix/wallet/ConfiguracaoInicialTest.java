package com.frabelovix.wallet;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ConfiguracaoInicialTest {
	
	@Test
	public void testDeveFuncionarConfiguracaoTestes(){
	 assertEquals(1,1);
	}

}
